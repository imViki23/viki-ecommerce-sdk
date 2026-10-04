package com.viki.api.orders.services;

import com.viki.api.orders.dtos.OrderDto;
import com.viki.api.orders.workflows.OrderWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final WorkflowClient workflowClient;

    public void createOrder(OrderDto orderDto) {
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue("OrderTaskQueue")
                .setWorkflowId("order-workflow-" + UUID.randomUUID())
                .build();
        OrderWorkflow workflow = workflowClient.newWorkflowStub(OrderWorkflow.class, options);
        WorkflowClient.start(workflow::createOrder, orderDto);
    }
}
