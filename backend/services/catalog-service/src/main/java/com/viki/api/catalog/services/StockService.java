package com.viki.api.catalog.services;

import com.viki.api.catalog.dtos.StockDto;
import com.viki.api.catalog.dtos.StockRequestDto;

import java.util.List;
import java.util.UUID;

public interface StockService {
    List<StockDto> getStocks(List<UUID> variantIds);
    List<StockDto> getStocksBatch(List<StockRequestDto> stockRequestDtos);
}
