package dev.rodrigomuller.stocksanalyzer.stock.dto.thirdparty.marketstack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EndOfDayTradingDTO {
    private Pagination pagination;
    private DailyTrading[] data;
}
