package com.viki.catalog.controllers;

import com.viki.catalog.dtos.ProductDto;
import com.viki.catalog.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/v1/product/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") UUID productId) {
        log.info("Received Request: productId = {}", productId);
        return productService.getProduct(productId)
                .map(productDto -> {
                    log.debug("Sending Response: {}", productDto);
                    return ResponseEntity.ok(productDto);
                })
                .orElse(ResponseEntity.noContent().build());
    }
}
