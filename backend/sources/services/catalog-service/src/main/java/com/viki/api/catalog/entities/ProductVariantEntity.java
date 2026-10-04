package com.viki.api.catalog.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name = "product_variants", schema = "catalog")
public class ProductVariantEntity {

    @Id
    @Column(name = "variant_id")
    private UUID variantId;

    @Column(name = "sku")
    private String sku;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "attributes", columnDefinition = "jsonb")
    private Map<String, String> attributes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}
