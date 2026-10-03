package com.viki.api.orders.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items", schema = "orders")
public class OrderItemEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "order_item_id")
    private UUID orderItemId;

    @Column(name = "variant_id")
    private UUID variantId;

    @Column(name = "vendor_id")
    private UUID vendorId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
