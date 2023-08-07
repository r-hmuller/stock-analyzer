package dev.rodrigomuller.stocksanalyzer.stock.dto;

import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
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

    private Company company;
}
