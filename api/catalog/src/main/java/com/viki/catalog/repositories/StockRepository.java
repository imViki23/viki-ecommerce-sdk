package com.viki.catalog.repositories;

import com.viki.catalog.entities.StockEntity;
import com.viki.catalog.entities.StockEntityId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StockRepository extends JpaRepository<StockEntity, StockEntityId> {
    List<StockEntity> findAllByVariantIdIn(List<UUID> variantIds);
}
