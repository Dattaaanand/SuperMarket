package com.supermarket.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionDTO {
    private Long id;
    private String name;
    private String description;
    private Integer productCount;
    private Long createdAt;
    private Long updatedAt;
}
