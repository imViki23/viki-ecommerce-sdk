package com.viki.api.catalog.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class StockDto {
    private UUID vendorId;
    private Integer quantity;
    private Double price;
}
