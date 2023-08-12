package dev.rodrigomuller.stocksanalyzer.stock.dto.thirdparty.marketstack;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class DailyTradingMessageDTO {
    private String companySymbol;
}
