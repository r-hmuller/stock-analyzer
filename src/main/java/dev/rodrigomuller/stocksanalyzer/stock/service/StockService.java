package dev.rodrigomuller.stocksanalyzer.stock.service;

import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;

public interface StockService {
    TradingRequestDTO getDailyData(Company company);
}
