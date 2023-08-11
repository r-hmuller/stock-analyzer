package dev.rodrigomuller.stocksanalyzer.stock.dto.thirdparty.marketstack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {
    private int limit;
    private int offset;
    private int count;
    private int total;
}
