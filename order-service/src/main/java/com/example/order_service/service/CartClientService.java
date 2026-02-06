package com.example.order_service.service;

import com.example.order_service.client.CartClient;
import com.example.order_service.dto.CartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartClientService {
    private final CartClient cartClient;
    public CartResponse getCartByUserId(Long userId){
        return cartClient.getCartByUserId(userId);
    }
}
