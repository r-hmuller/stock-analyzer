package dev.rodrigomuller.stocksanalyzer.stock.dto;

import dev.rodrigomuller.stocksanalyzer.stock.entity.Sector;
import dev.rodrigomuller.stocksanalyzer.stock.entity.TradingMarket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String symbol;

    @NotNull
    private Sector sector;

    @NotNull
    private TradingMarket tradingMarket;
}
