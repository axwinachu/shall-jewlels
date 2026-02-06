package com.example.cart_service.dto;

import lombok.Data;

@Data
public class CartUpdateDto {
    private Long productId;
    private Long quantity;
}
