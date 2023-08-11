package dev.rodrigomuller.stocksanalyzer.stock.service.providers;

import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "stock.api.provider", havingValue = "alphavantage")
public class AlphaVantageService implements StockService {

    @Value("${alphavantage.api}")
    private String alphaVantageApiKey;

    @Value("${alphavantage.url}")
    private String alphaVantageApiUrl;

    @Override
    public TradingRequestDTO getDailyData(Company company) {
        return null;
    }

    private void makeRequest(String requestData) {
        String url = alphaVantageApiUrl + requestData + "&apiKey=" + alphaVantageApiKey;
        //Use Rest Template :)
    }
}
