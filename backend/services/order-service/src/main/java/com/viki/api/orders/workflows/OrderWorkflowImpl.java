package com.viki.api.orders.workflows;

import com.viki.api.orders.activities.OrderActivities;
import com.viki.api.orders.dtos.OrderDto;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;

import java.time.Duration;

@WorkflowImpl(taskQueues = "OrderTaskQueue")
public class OrderWorkflowImpl implements OrderWorkflow {

    private final OrderActivities orderActivities = Workflow.newActivityStub(
            OrderActivities.class,
            ActivityOptions.newBuilder()
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .build()
    );

    @Override
    public void createOrder(OrderDto orderDto) {
        orderActivities.createOrder(orderDto);
    }
}
