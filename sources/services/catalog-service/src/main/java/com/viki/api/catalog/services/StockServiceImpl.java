package com.viki.api.catalog.services;

import com.viki.api.catalog.dtos.StockDto;
import com.viki.api.catalog.dtos.StockRequestDto;
import com.viki.api.catalog.mappers.StockMapper;
import com.viki.api.catalog.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @Override
    public List<StockDto> getStocks(List<UUID> variantIds) {
        return stockRepository.findAllByVariantIdIn(variantIds)
                .stream()
                .map(stockMapper::mapToStockDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getStocksBatch(List<StockRequestDto> stockRequestDtos) {
        List<UUID> variantIds = stockRequestDtos.stream()
                .map(StockRequestDto::getVariantId)
                .collect(Collectors.toList());
        List<UUID> vendorIds = stockRequestDtos.stream()
                .map(StockRequestDto::getVendorId)
                .collect(Collectors.toList());
        Map<UUID, UUID> variantVendorMap = stockRequestDtos.stream()
                .collect(Collectors.toMap(StockRequestDto::getVariantId, StockRequestDto::getVendorId));
        return stockRepository.findAllByVariantIdInAndVendorIdIn(variantIds, vendorIds)
                .stream()
                .filter(stockEntity -> variantVendorMap.get(stockEntity.getVariantId()).equals(stockEntity.getVendorId()))
                .map(stockMapper::mapToStockDto)
                .collect(Collectors.toList());
    }


}
