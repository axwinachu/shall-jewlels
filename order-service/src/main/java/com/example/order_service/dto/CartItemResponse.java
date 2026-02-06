package com.example.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Long productId;
    private String productName;
    private String brand;
    private BigDecimal price;
    private Long quantity;
    private String mainImageUrl;
}
