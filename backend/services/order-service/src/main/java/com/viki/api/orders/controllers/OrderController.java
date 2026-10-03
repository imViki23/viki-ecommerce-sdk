package com.viki.api.orders.controllers;

import com.viki.api.orders.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.viki.api.orders.dtos.OrderDto;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    
    @PostMapping("/v1/order")
    public ResponseEntity<Void> createOrder(@RequestBody OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.ok().build();
    }
}
