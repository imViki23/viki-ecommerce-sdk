package com.viki.catalog.mappers;

import com.viki.catalog.dtos.ProductDto;
import com.viki.catalog.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    @Mapping(target = "productId", source = "productId")
    ProductDto mapToProductDto(ProductEntity productEntity);
}
