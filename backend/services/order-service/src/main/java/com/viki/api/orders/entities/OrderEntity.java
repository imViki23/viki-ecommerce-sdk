package com.viki.api.orders.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "status")
    private String status;

    @Column(name = "address_id")
    private UUID addressId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemEntity> items;
}
