package com.example.cart_service.service;


import com.example.cart_service.model.CartItem;
import com.example.cart_service.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    public Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId) {
        return cartItemRepository.findByCartAndProductId(cartId,productId);
    }

    public CartItem save(CartItem newItem) {
        return cartItemRepository.save(newItem);
    }
}
