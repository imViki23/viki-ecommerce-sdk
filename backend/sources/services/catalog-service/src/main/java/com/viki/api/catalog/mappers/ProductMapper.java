package com.viki.api.catalog.mappers;

import com.viki.api.catalog.dtos.ProductDto;
import com.viki.api.catalog.dtos.ProductVariantDto;
import com.viki.api.catalog.dtos.StockDto;
import com.viki.api.catalog.entities.ProductEntity;
import com.viki.api.catalog.entities.ProductVariantEntity;
import com.viki.api.catalog.entities.StockEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "slug", source = "slug")
    @Mapping(target = "variants", source = "variants")
    ProductDto mapToProductDto(ProductEntity productEntity);

    @Mapping(target = "variantId", source = "variantId")
    @Mapping(target = "sku", source = "sku")
    @Mapping(target = "attributes", source = "attributes")
    @Mapping(target = "stocks", ignore = true)
    ProductVariantDto mapToProductVariantDto(ProductVariantEntity productVariantEntity);

    @Mapping(target = "vendorId", source = "vendorId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    StockDto mapToStockDto(StockEntity stockEntity);

}
