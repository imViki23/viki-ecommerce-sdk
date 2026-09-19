package com.viki.catalog.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class ProductDto implements Serializable {
    private UUID productId;
    private String name;
    private String slug;
    private List<ProductVariantDto> variants;
}
