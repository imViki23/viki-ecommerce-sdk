package com.viki.api.catalog.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "stocks", schema = "catalog")
@IdClass(StockEntityId.class)
public class StockEntity {

    @Id
    @Column(name = "variant_id")
    private UUID variantId;

    @Id
    @Column(name = "vendor_id")
    private UUID vendorId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

}
