package com.example.cart_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "product-service")
public interface ProductClient {
    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    @PostMapping("/products/list")
    List<com.example.cart_service.dto.ProductResponse> getCartProducts(@RequestBody List<Long> productIds);

    class ProductResponse{
        public Long productId;
        public Long stockQuantity;
        public BigDecimal price;
    }
}
