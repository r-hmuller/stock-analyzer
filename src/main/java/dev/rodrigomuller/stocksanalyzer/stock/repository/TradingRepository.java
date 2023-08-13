package dev.rodrigomuller.stocksanalyzer.stock.repository;

import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Trading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingRepository extends JpaRepository<Trading, Long> {
    Page<Trading> findAllByCompany(Company company, Pageable pageable);
}
