package com.example.cart_service.service;

import com.example.cart_service.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductClientService {
    private final ProductClient productClient;

    public ProductClient.ProductResponse findProductById(Long productId) {
        return productClient.getProductById(productId);
    }
}
