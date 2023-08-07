package dev.rodrigomuller.stocksanalyzer.stock.repository;

import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
