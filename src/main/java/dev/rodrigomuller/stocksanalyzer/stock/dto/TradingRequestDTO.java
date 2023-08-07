package dev.rodrigomuller.stocksanalyzer.stock.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradingRequestDTO {
    @NotNull
    @PastOrPresent
    private LocalDate tradingDate;

    @PositiveOrZero
    private Integer volume;

    @NotNull
    @PositiveOrZero
    private Float openingPrice;

    @NotNull
    @PositiveOrZero
    private Float closingPrice;
}
