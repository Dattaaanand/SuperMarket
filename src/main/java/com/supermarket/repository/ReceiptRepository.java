package com.supermarket.repository;

import com.supermarket.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByCreatedAtBetween(Long startDate, Long endDate);
    
    @Query("SELECT r FROM Receipt r ORDER BY r.createdAt DESC")
    List<Receipt> findAllOrderByDateDesc();
    
    @Query("SELECT SUM(r.totalAmount) FROM Receipt r WHERE r.createdAt BETWEEN :startDate AND :endDate")
    Double sumTotalAmountBetweenDates(Long startDate, Long endDate);
}
