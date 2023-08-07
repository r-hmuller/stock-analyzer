package dev.rodrigomuller.stocksanalyzer.stock.dto;

import dev.rodrigomuller.stocksanalyzer.stock.entity.Sector;
import dev.rodrigomuller.stocksanalyzer.stock.entity.TradingMarket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponseDTO {
    private Long id;
    private String name;
    private String symbol;
    private Sector sector;
    private TradingMarket tradingMarket;
}
