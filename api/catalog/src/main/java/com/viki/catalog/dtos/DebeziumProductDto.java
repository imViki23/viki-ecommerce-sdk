package com.viki.catalog.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class DebeziumProductDto implements Serializable {
    @JsonProperty(value = "product_id")
    private UUID productId;
}
