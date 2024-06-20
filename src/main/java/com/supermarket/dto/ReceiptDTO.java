package com.supermarket.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptDTO {
    private Long id;
    private String receiptNumber;
    private Long cashierId;
    private String cashierName;
    private Double totalAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Integer itemsCount;
    private String paymentMethod;
    private String items;
    private String notes;
    private Long createdAt;
}
