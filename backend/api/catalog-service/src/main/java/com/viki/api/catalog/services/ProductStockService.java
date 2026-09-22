package com.viki.api.catalog.services;

import com.viki.api.catalog.dtos.ProductDto;

import java.util.UUID;

public interface ProductStockService {
    ProductDto getProduct(UUID productId);
}
