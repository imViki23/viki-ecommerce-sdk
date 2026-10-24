package com.viki.api.orders.workflows;

import com.viki.api.orders.dtos.OrderDto;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderWorkflow {

    @WorkflowMethod
    void createOrder(OrderDto orderDto);
}
