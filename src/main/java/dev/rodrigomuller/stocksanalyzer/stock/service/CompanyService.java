package dev.rodrigomuller.stocksanalyzer.stock.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import dev.rodrigomuller.stocksanalyzer.stock.dto.CompanyRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.CompanyResponseDTO;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Sector;
import dev.rodrigomuller.stocksanalyzer.stock.entity.TradingMarket;
import dev.rodrigomuller.stocksanalyzer.stock.mapper.CompanyMapper;
import dev.rodrigomuller.stocksanalyzer.stock.mapper.TradingMapper;
import dev.rodrigomuller.stocksanalyzer.stock.repository.CompanyRepository;
import dev.rodrigomuller.stocksanalyzer.stock.repository.TradingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    @Value("${populate-database}")
    private String populateDatabase;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper, TradingRepository tradingRepository, TradingMapper tradingMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public CompanyResponseDTO find(Long id) {
        Company company = findById(id);
        return companyMapper.toResponseDTO(company);
    }

    public List<CompanyResponseDTO> listAll() {
        Page<Company> companies = companyRepository.findAll(PageRequest.of(0, 20));
        return companyMapper.listResponseDTO(companies.toList());
    }

    public CompanyResponseDTO save(CompanyRequestDTO companyRequestDTO) {
        Company company = companyMapper.fromRequestDTO(companyRequestDTO);
        company = companyRepository.save(company);
        return companyMapper.toResponseDTO(company);
    }

    public void delete(Long id) {
        Company company = findById(id);
        companyRepository.delete(company);
    }

    private Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabase() {
        if (Boolean.parseBoolean(populateDatabase)) {
            System.out.println("Populating database from csv file");
            try {
                importNasdaqCompanies();
            } catch (FileNotFoundException e) {
                System.out.println("Error while importing the companies");
                throw new RuntimeException(e);
            }
        }
    }

    @Transactional
    public void importNasdaqCompanies() throws FileNotFoundException, RuntimeException {
        System.out.println("Importing Nasdaq companies");
        List<List<String>> csvLines = readCsvFromResources("nasdaq-listed.csv");
        List<List<String>> stocks = removeETFRows(csvLines);
        List<Company> companies = new ArrayList<>();
        stocks.forEach(stock -> {
            Company company = new Company();
            company.setSymbol(stock.get(0));
            company.setName(stock.get(1));
            company.setSector(Sector.OTHER);
            company.setTradingMarket(TradingMarket.NASDAQ);

            companies.add(company);
        });

        companyRepository.saveAll(companies);
    }

    private List<List<String>> readCsvFromResources(String fileName) throws FileNotFoundException, RuntimeException {
        String path = "classpath:" + fileName;
        File file = ResourceUtils.getFile(path);
        List<List<String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }

        return records;
    }

    private List<List<String>> removeETFRows(List<List<String>> rows) {
        return rows.stream().filter(row -> row.get(7).equals("N")).toList();
    }
}
