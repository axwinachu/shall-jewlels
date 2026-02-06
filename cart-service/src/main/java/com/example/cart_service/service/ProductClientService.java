package com.example.cart_service.service;

import com.example.cart_service.client.ProductClient;
import com.example.cart_service.dto.ProductResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductClientService {
    private final ProductClient productClient;

    public ProductClient.ProductResponse findProductById(Long productId) {
        return productClient.getProductById(productId);
    }
    public List<ProductResponse> getAllProductsByIds(List<Long> ids){
        return productClient.getCartProducts(ids);
    }
}
