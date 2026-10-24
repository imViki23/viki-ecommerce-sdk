package com.viki.api.catalog.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class StockRequestDto {
    private UUID variantId;
    private UUID vendorId;
}
