package com.supermarket.repository;

import com.supermarket.entity.InventoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryLogRepository extends JpaRepository<InventoryLog, Long> {
    List<InventoryLog> findByProductIdOrderByCreatedAtDesc(Long productId);
    List<InventoryLog> findByReasonOrderByCreatedAtDesc(String reason);
    List<InventoryLog> findByCreatedAtBetween(Long startDate, Long endDate);
}
