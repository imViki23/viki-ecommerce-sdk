package com.viki.catalog.services;

import com.viki.catalog.dtos.ProductDto;
import com.viki.catalog.mappers.ProductMapper;
import com.viki.catalog.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Cacheable(value = "products", key = "#productId")
    public ProductDto getProduct(UUID productId) {
        log.warn("Product {} not found in cache", productId);
        return productRepository.findById(productId)
                .map(productMapper::mapToProductDto)
                .orElse(null);
    }

    @Override
    @CacheEvict(value = "products", key = "#productId")
    public void evictProductCache(UUID productId) { }


}
