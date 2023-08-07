package dev.rodrigomuller.stocksanalyzer.stock.service;

import dev.rodrigomuller.stocksanalyzer.stock.dto.CompanyRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.CompanyResponseDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingResponseDTO;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Trading;
import dev.rodrigomuller.stocksanalyzer.stock.mapper.CompanyMapper;
import dev.rodrigomuller.stocksanalyzer.stock.mapper.TradingMapper;
import dev.rodrigomuller.stocksanalyzer.stock.repository.CompanyRepository;
import dev.rodrigomuller.stocksanalyzer.stock.repository.TradingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    private final TradingMapper tradingMapper;

    private final TradingRepository tradingRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper, TradingRepository tradingRepository, TradingMapper tradingMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.tradingMapper = tradingMapper;
        this.tradingRepository = tradingRepository;
    }

    public CompanyResponseDTO find(Long id) {
        Company company = findById(id);
        return companyMapper.toResponseDTO(company);
    }

    public List<CompanyResponseDTO> listAll() {
        Page<Company> companies = companyRepository.findAll(PageRequest.of(0, 20));
        return companyMapper.listResponseDTO(companies.toList());
    }

    public CompanyResponseDTO save(CompanyRequestDTO companyRequestDTO) {
        Company company = companyMapper.fromRequestDTO(companyRequestDTO);
        company = companyRepository.save(company);
        return companyMapper.toResponseDTO(company);
    }

    public void delete(Long id) {
        Company company = findById(id);
        companyRepository.delete(company);
    }

    private Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    public TradingResponseDTO addTrading(Long companyId, TradingRequestDTO tradingRequestDTO) {
        Company company = companyRepository.getReferenceById(companyId);

        Trading trading = tradingMapper.fromRequestDTO(tradingRequestDTO);
        trading.setCompany(company);
        trading = tradingRepository.save(trading);
        company.getTradings().add(trading);

        return tradingMapper.toResponseDTO(trading);
    }

    public Set<TradingResponseDTO> listTrading(Long companyId) {
        Company company = findById(companyId);
        Set<Trading> tradings = company.getTradings();
        System.out.println("--------" + company.getTradings());
        return tradingMapper.listResponseDTO(tradings);
    }
}
