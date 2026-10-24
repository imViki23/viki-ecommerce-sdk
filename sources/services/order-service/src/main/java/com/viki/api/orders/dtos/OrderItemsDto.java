package com.viki.api.orders.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class OrderItemsDto {
    private UUID variantId;
    private UUID vendorId;
    private Integer quantity;
}
