package dev.rodrigomuller.stocksanalyzer.stock.mapper;

import dev.rodrigomuller.stocksanalyzer.stock.dto.CompanyRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.CompanyResponseDTO;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company fromRequestDTO(CompanyRequestDTO dto);

    CompanyResponseDTO toResponseDTO(Company company);

    List<CompanyResponseDTO> listResponseDTO(List<Company> companies);
}
