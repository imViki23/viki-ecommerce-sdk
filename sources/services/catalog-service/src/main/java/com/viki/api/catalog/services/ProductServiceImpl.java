package com.viki.api.catalog.services;

import com.viki.api.catalog.dtos.ProductDto;
import com.viki.api.catalog.dtos.ProductVariantDto;
import com.viki.api.catalog.dtos.StockDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductCacheService productCacheService;
    private final StockServiceImpl stockServiceImpl;

    @Override
    public ProductDto getProduct(UUID productId) {
        ProductDto productDto = productCacheService.getProduct(productId);
        if (productDto == null) {
            return null;
        }
        if (productDto.getVariants() != null) {
            List<UUID> variantIds = productDto.getVariants().stream().map(ProductVariantDto::getVariantId).toList();
            List<StockDto> allStockDtos = stockServiceImpl.getStocks(variantIds);
            for (ProductVariantDto productVariantDto : productDto.getVariants()) {
                List<StockDto> stockDtos = allStockDtos.stream()
                        .filter(s -> s.getVariantId().equals(productVariantDto.getVariantId()))
                        .toList();
                productVariantDto.setStocks(stockDtos);
            }
        }
        return productDto;
    }

}
