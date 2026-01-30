package com.example.cart_service.service;

import com.example.cart_service.model.Cart;
import com.example.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private  final CartRepository cartRepository;
    public Optional<Cart> findCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
