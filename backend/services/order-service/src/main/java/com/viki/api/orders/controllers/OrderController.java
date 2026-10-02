package com.viki.api.orders.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viki.api.orders.dtos.OrderDto;

@RestController
public class OrderController {
    
    @PostMapping("/v1/order")
    public void createOrder(OrderDto orderDto) {
        // Logic to create an order
    }
}
