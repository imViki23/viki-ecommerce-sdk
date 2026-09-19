package com.viki.catalog.services;

import com.viki.catalog.dtos.ProductDto;
import com.viki.catalog.dtos.ProductVariantDto;
import com.viki.catalog.dtos.StockDto;
import com.viki.catalog.entities.StockEntity;
import com.viki.catalog.mappers.ProductMapper;
import com.viki.catalog.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final StockRepository stockRepository;

    @Override
    public ProductDto getProduct(UUID productId) {
        ProductDto productDto = productService.getProduct(productId);
        if (productDto == null) {
            return null;
        }
        if (productDto.getVariants() != null) {
            List<UUID> variantIds = productDto.getVariants().stream().map(ProductVariantDto::getVariantId).toList();
            List<StockEntity> stockEntities = stockRepository.findAllByVariantIdIn(variantIds);
            for (ProductVariantDto productVariantDto : productDto.getVariants()) {
                List<StockDto> stockDtoList = stockEntities.stream()
                        .filter(stockEntity -> stockEntity.getVariantId().equals(productVariantDto.getVariantId()))
                        .map(productMapper::mapToStockDto)
                        .toList();
                productVariantDto.setStocks(stockDtoList);
            }
        }
        return productDto;
    }
}
