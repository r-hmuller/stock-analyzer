package dev.rodrigomuller.stocksanalyzer.stock.service;

import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingResponseDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.thirdparty.marketstack.DailyTradingMessageDTO;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Trading;
import dev.rodrigomuller.stocksanalyzer.stock.mapper.TradingMapper;
import dev.rodrigomuller.stocksanalyzer.stock.queue.TradingProducer;
import dev.rodrigomuller.stocksanalyzer.stock.repository.CompanyRepository;
import dev.rodrigomuller.stocksanalyzer.stock.repository.TradingRepository;
import dev.rodrigomuller.stocksanalyzer.stock.service.providers.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TradingService {
    private final StockService stockService;

    private final TradingRepository tradingRepository;

    private final CompanyRepository companyRepository;

    private final TradingMapper tradingMapper;

    private final TradingProducer tradingProducer;

    @Autowired
    public TradingService(StockService stockService,
                          TradingRepository tradingRepository,
                          CompanyRepository companyRepository,
                          TradingMapper tradingMapper,
                          TradingProducer tradingProducer
    ) {
        this.stockService = stockService;
        this.tradingRepository = tradingRepository;
        this.companyRepository = companyRepository;
        this.tradingMapper = tradingMapper;
        this.tradingProducer = tradingProducer;
    }

    public void emitEvent(Long id) {
        Company company = companyRepository.findById(id).orElseThrow();
        DailyTradingMessageDTO message = DailyTradingMessageDTO
                .builder()
                .companySymbol(company.getSymbol())
                .build();
        tradingProducer.sendMessage(message);
    }

    public void syncTrading(DailyTradingMessageDTO message) {
        try {
            Company company = companyRepository.findOneBySymbol(message.getCompanySymbol()).orElseThrow();
            TradingRequestDTO tradingDTO = stockService.getDailyData(company);
            addTrading(company.getId(), tradingDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public TradingResponseDTO addTrading(Long companyId, TradingRequestDTO tradingRequestDTO) {
        Company company = companyRepository.getReferenceById(companyId);

        Trading trading = tradingMapper.fromRequestDTO(tradingRequestDTO);
        trading.setCompany(company);
        trading = tradingRepository.save(trading);

        Set<Trading> tradings = company.getTradings();
        boolean addedTrading = tradings.add(trading);

        if (addedTrading) {
            company.setTradings(tradings);
            companyRepository.save(company);
        }

        return tradingMapper.toResponseDTO(trading);
    }

    public Page<TradingResponseDTO> listTrading(Long companyId, Pageable pageable) {
        Company company = companyRepository.findById(companyId).orElseThrow();

        return tradingRepository.findAllByCompany(company, pageable).map(tradingMapper::toResponseDTO);
    }
}
