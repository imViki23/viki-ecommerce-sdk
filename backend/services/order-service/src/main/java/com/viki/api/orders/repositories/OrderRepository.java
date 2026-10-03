package com.viki.api.orders.repositories;

import com.viki.api.orders.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
