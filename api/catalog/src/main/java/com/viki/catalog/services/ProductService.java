package com.viki.catalog.services;

import com.viki.catalog.dtos.ProductDto;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Optional<ProductDto> getProduct(UUID productId);
}
