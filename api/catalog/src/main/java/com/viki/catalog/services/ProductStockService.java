package com.viki.catalog.services;

import com.viki.catalog.dtos.ProductDto;

import java.util.UUID;

public interface ProductStockService {
    ProductDto getProduct(UUID productId);
}
