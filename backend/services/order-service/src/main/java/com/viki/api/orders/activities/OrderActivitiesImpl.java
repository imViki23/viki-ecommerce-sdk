package com.viki.api.orders.activities;

import com.viki.api.orders.dtos.OrderDto;
import com.viki.api.orders.entities.OrderEntity;
import com.viki.api.orders.entities.OrderItemEntity;
import com.viki.api.orders.repositories.OrderRepository;
import com.viki.api.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderActivitiesImpl implements OrderActivities {

    private final OrderRepository orderRepository;
    private final SecurityUtils securityUtils;

    @Override
    public void createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = OrderEntity.builder()
                .userId(UUID.fromString("179e0d45-bcbd-40aa-8155-980baa638737"))
                .addressId(orderDto.getAddressId())
                .status("OrderCreated")
                .build();
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        List<OrderItemEntity> orderItemEntities = orderDto.getItems()
                .stream()
                .map(item -> OrderItemEntity.builder()
                        .variantId(item.getVariantId())
                        .vendorId(item.getVendorId())
                        .quantity(item.getQuantity())
                        .unitPrice(500)
                        .order(savedOrderEntity)
                        .build())
                .toList();
        savedOrderEntity.setItems(new ArrayList<>(orderItemEntities));
        orderRepository.save(savedOrderEntity);
    }
}
