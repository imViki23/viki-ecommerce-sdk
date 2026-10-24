package com.viki.api.catalog.controllers;

import com.viki.api.catalog.dtos.StockDto;
import com.viki.api.catalog.dtos.StockRequestDto;
import com.viki.api.catalog.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/v1/stocks/batch")
    public ResponseEntity<List<StockDto>> getStocksBatch(@RequestBody List<StockRequestDto> stockRequestDtos) {
        List<StockDto> stockDtos = stockService.getStocksBatch(stockRequestDtos);
        return ResponseEntity.ok(stockDtos);
    }
}
