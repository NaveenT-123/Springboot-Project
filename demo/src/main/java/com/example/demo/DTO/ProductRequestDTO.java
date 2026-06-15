package com.example.demo.DTO;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class ProductRequestDTO {
    
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Integer stockQuantity;
    private String imageUrl;
}
