package dev.rodrigomuller.stocksanalyzer.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradingResponseDTO {
    private Long id;

    private LocalDate tradingDate;

    private Integer volume;

    private Float openingPrice;

    private Float closingPrice;

    private Float highestPrice;

    private Float lowestPrice;
}
