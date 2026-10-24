package com.viki.api.catalog.mappers;

import com.viki.api.catalog.dtos.StockDto;
import com.viki.api.catalog.entities.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StockMapper {

    @Mapping(target = "vendorId", source = "vendorId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "variantId", source = "variantId")
    StockDto mapToStockDto(StockEntity stockEntity);
}
