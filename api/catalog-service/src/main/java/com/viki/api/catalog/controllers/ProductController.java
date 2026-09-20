package com.viki.api.catalog.controllers;

import com.viki.api.catalog.dtos.ProductDto;
import com.viki.api.catalog.services.ProductStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductStockService productStockService;

    @GetMapping("/v1/product/{productId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") UUID productId) {
        ProductDto productDto = productStockService.getProduct(productId);
        if (productDto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productDto);
    }
}
