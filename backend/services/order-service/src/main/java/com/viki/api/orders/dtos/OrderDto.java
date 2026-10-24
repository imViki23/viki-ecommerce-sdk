package com.viki.api.orders.dtos;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
public class OrderDto {
    private UUID addressId;
    private List<OrderItemsDto> items;
}
