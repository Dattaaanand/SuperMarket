package com.supermarket.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Integer reorderLevel;
    private String barcode;
    private Long sectionId;
    private String sectionName;
    private Boolean isActive;
    private Long createdAt;
    private Long updatedAt;
}
