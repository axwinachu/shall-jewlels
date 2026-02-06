package com.example.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long productId;
    private String productName;
    private String brand;
    private BigDecimal price;
    private String mainImageUrl;
    private List<String> imageUrl;
    private Integer reviewCount;
    private Long review;
}
