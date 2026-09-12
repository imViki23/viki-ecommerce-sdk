package com.viki.catalog.services;

import com.viki.catalog.dtos.ProductDto;
import com.viki.catalog.mappers.ProductMapper;
import com.viki.catalog.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Optional<ProductDto> getProduct(UUID productId) {
        return productRepository.findById(productId)
                .map(productEntity -> {
                    log.debug("Fetched data {} from products table for productId {}", productEntity, productId);
                    return productMapper.mapToProductDto(productEntity);
                });
    }
}
