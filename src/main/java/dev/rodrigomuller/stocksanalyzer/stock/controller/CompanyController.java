package dev.rodrigomuller.stocksanalyzer.stock.controller;

import dev.rodrigomuller.stocksanalyzer.stock.dto.CompanyRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.CompanyResponseDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingResponseDTO;
import dev.rodrigomuller.stocksanalyzer.stock.service.CompanyService;
import dev.rodrigomuller.stocksanalyzer.stock.service.TradingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    private final TradingService tradingService;

    @Autowired
    public CompanyController(CompanyService companyService, TradingService tradingService) {
        this.companyService = companyService;
        this.tradingService = tradingService;
    }

    @GetMapping()
    public ResponseEntity<List<CompanyResponseDTO>> listAll() {
        List<CompanyResponseDTO> companies = companyService.listAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> find(@PathVariable Long id) {
        CompanyResponseDTO company = companyService.find(id);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CompanyResponseDTO> save(@RequestBody @Valid CompanyRequestDTO companyRequestDTO) {
        CompanyResponseDTO company = companyService.save(companyRequestDTO);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        companyService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/tradings")
    public ResponseEntity<TradingResponseDTO> saveTrading(@PathVariable Long id, @RequestBody @Valid TradingRequestDTO request) {
        TradingResponseDTO trading = tradingService.addTrading(id, request);
        return new ResponseEntity<>(trading, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/tradings")
    public ResponseEntity<Set<TradingResponseDTO>> getTradings(@PathVariable Long id) {
        Set<TradingResponseDTO> tradings = tradingService.listTrading(id);
        System.out.println(tradings);
        return new ResponseEntity<>(tradings, HttpStatus.OK);
    }

    @PostMapping("/{id}/tradings/sync")
    public ResponseEntity<?> syncCompanyTrading(@PathVariable Long id) {
        tradingService.syncTrading(id);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
