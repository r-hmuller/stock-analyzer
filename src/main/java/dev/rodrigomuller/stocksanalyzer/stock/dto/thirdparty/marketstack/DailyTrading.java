package dev.rodrigomuller.stocksanalyzer.stock.dto.thirdparty.marketstack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyTrading {
    private Float adj_high;
    private Float adj_low;
    private Float adj_open;
    private Float adj_close;
    private Float adj_volume;

    private Float high;
    private Float low;
    private Float open;
    private Float close;
    private Float volume;
    
    private ZonedDateTime date;
}
