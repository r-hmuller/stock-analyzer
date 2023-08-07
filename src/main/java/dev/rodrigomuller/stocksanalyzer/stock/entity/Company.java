package dev.rodrigomuller.stocksanalyzer.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String symbol;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    @Enumerated(EnumType.STRING)
    private TradingMarket tradingMarket;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private Set<Trading> tradings;
}
