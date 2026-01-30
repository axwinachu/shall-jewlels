package com.example.cart_service.facade;

import com.example.cart_service.dto.CartItemResponse;
import com.example.cart_service.dto.CartResponse;
import com.example.cart_service.model.Cart;
import com.example.cart_service.model.CartItem;
import com.example.cart_service.service.CartItemService;
import com.example.cart_service.service.CartService;
import com.example.cart_service.service.ProductClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartFacade {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final ProductClientService productClientService;

    public String addProductToCart(Long userId, Long productId, Long quantity) {

        var product = productClientService.findProductById(productId);

        if (product.stockQuantity < quantity) {
            return "OUT_OF_STOCK";
        }

        Cart cart = cartService.findCartByUserId(userId)
                .orElseGet(() -> cartService.save(
                        Cart.builder()
                                .userId(userId)
                                .createdAt(LocalDateTime.now())
                                .items(new ArrayList<>())
                                .build()
                ));
        CartItem cartItem = cartItemService
                .findByCartIdAndProductId(cart.getId(), productId)
                .orElse(null);
        if (cartItem == null) {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .productId(productId)
                    .quantity(quantity)
                    .price(product.price)
                    .build();

            cartItemService.save(newItem);
            return "PRODUCT_ADDED_SUCCESSFULLY";
        }

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemService.save(cartItem);
        return "PRODUCT_QUANTITY_UPDATED";
    }

    public CartResponse viewCart(Long userId) {
        Cart cart=cartService.findCartByUserId(userId)
                .orElseThrow(()->new RuntimeException("Cart Not found"));

        List<Long> productIds=cart.getItems().stream().map(CartItem::getProductId).toList();
        List<CartItemResponse> cartItems=productClientService.

    }
}
