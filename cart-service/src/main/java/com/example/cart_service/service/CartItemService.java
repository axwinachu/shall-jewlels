package com.example.cart_service.service;


import com.example.cart_service.model.Cart;
import com.example.cart_service.model.CartItem;
import com.example.cart_service.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    public Optional<CartItem> findByCartIdAndProductId(Cart cart, Long productId) {
        return cartItemRepository.findByCartAndProductId(cart,productId);
    }

    public CartItem save(CartItem newItem) {
        return cartItemRepository.save(newItem);
    }
    public void delete(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }
}
