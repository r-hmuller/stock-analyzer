package dev.rodrigomuller.stocksanalyzer.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "company")
public class Trading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "trading_date")
    private LocalDate tradingDate;

    @Column
    private Integer volume;

    @Column(name = "opening_price", nullable = false)
    private Float openingPrice;

    @Column(name = "closing_price", nullable = false)
    private Float closingPrice;

    @Column(name = "highest_price")
    private Float highestPrice;

    @Column(name = "lowest_price")
    private Float lowestPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;
}
