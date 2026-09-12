package com.viki.catalog.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "products", schema = "catalog")
public class ProductEntity {

    @Id
    private UUID productId;
}
