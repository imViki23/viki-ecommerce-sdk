package com.viki.catalog.services;

import com.viki.catalog.dtos.ProductDto;

import java.util.UUID;

public interface ProductService {
    ProductDto getProduct(UUID productId);
    void evictProductCache(UUID productId);
}
