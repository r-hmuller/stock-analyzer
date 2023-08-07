package dev.rodrigomuller.stocksanalyzer.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Trading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "trading_date")
    private LocalDate tradingDate;

    @Column
    private Integer volume;

    @Column(name = "opening_price")
    private Float openingPrice;

    @Column(name = "closing_price")
    private Float closingPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
