package com.example.product_service.dto;

import com.example.product_service.model.Enums.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private Long productId;
    private String productName;
    private String shortDescription;
    private String description;
    private String brand;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Long taxPercentage;
    private Long stockQuantity;
    private String mainImageUrl;
    private List<String> imageUrl;
    private Availability availability;
    private Integer reviewCount;
    private Long review;
}
