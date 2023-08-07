package dev.rodrigomuller.stocksanalyzer.stock.mapper;

import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingRequestDTO;
import dev.rodrigomuller.stocksanalyzer.stock.dto.TradingResponseDTO;
import dev.rodrigomuller.stocksanalyzer.stock.entity.Trading;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TradingMapper {
    TradingMapper INSTANCE = Mappers.getMapper(TradingMapper.class);

    Trading fromRequestDTO(TradingRequestDTO dto);

    TradingResponseDTO toResponseDTO(Trading trading);

    Set<TradingResponseDTO> listResponseDTO(Set<Trading> tradings);
}
