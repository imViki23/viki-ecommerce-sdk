package com.viki.api.orders.activities;

import com.viki.api.orders.dtos.OrderDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrderActivities {

    @ActivityMethod
    void createOrder(OrderDto orderDto);
}
