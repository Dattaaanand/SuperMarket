package com.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String receiptNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id", nullable = false)
    private Employee cashier;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private Double taxAmount;

    @Column(nullable = false)
    private Double discountAmount = 0.0;

    @Column(name = "items_count")
    private Integer itemsCount;

    @Column(name = "payment_method")
    private String paymentMethod; // CASH, CARD, BOTH

    @Column(columnDefinition = "TEXT")
    private String items; // JSON format

    @Column(name = "created_at", updatable = false)
    private Long createdAt = System.currentTimeMillis();

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}
