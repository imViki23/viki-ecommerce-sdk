package com.viki.api.catalog.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class ProductVariantDto implements Serializable {
    private UUID variantId;
    private String sku;
    private Map<String, String> attributes;
    private List<StockDto> stocks;
}
