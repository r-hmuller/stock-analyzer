package dev.rodrigomuller.stocksanalyzer.stock.repository;

import dev.rodrigomuller.stocksanalyzer.stock.entity.Trading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingRepository extends JpaRepository<Trading, Long> {
}
