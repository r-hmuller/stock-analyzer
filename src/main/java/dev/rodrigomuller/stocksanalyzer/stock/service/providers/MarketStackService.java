package dev.rodrigomuller.stocksanalyzer.stock.service.providers;

import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.thirdparty.marketstack.DailyTrading;
import dev.rodrigomuller.stocksanalyzer.stock.dto.thirdparty.marketstack.EndOfDayTradingDTO;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@ConditionalOnProperty(value = "stock.api.provider", havingValue = "marketstack")
public class MarketStackService implements StockService {
    private final WebClient.Builder builder;
    private WebClient webClient;
    @Value("${marketstack.api}")
    private String apiKey;
    @Value("${marketstack.url}")
    private String baseUrl;

    public MarketStackService(WebClient.Builder webClientBuilder) {
        this.builder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    @Override
    public TradingRequestDTO getDailyData(Company company) {
        String url = "/eod/latest?access_key=" + apiKey + "&symbols=" + company.getSymbol();
        EndOfDayTradingDTO apiDTO = this.webClient.get().uri(url)
                .retrieve().bodyToMono(EndOfDayTradingDTO.class).block();

        TradingRequestDTO tradingDTO = new TradingRequestDTO();
        assert apiDTO != null;
        DailyTrading tradingData = apiDTO.getData()[0];

        Float openingPrice = (tradingData.getAdj_open() != null) ? tradingData.getAdj_open() : tradingData.getOpen();
        Float closingPrice = (tradingData.getAdj_close() != null) ? tradingData.getAdj_close() : tradingData.getClose();
        Float highestPrice = (tradingData.getAdj_high() != null) ? tradingData.getAdj_high() : tradingData.getHigh();
        Float lowestPrice = (tradingData.getAdj_low() != null) ? tradingData.getAdj_low() : tradingData.getLow();
        int volume = (tradingData.getAdj_volume() != null) ? tradingData.getAdj_volume().intValue() : tradingData.getVolume().intValue();

        tradingDTO.setTradingDate(tradingData.getDate().toLocalDate());
        tradingDTO.setVolume(volume);
        tradingDTO.setClosingPrice(closingPrice);
        tradingDTO.setOpeningPrice(openingPrice);
        tradingDTO.setLowestPrice(lowestPrice);
        tradingDTO.setHighestPrice(highestPrice);

        return tradingDTO;
    }
}
