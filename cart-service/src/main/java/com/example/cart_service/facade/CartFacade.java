package com.example.cart_service.facade;

import com.example.cart_service.dto.CartItemResponse;
import com.example.cart_service.dto.ProductResponse;
import com.example.cart_service.dto.CartResponse;
import com.example.cart_service.dto.CartUpdateDto;
import com.example.cart_service.exception.CartNotFound;
import com.example.cart_service.exception.InvalidQuantity;
import com.example.cart_service.exception.ProductNotFound;
import com.example.cart_service.model.Cart;
import com.example.cart_service.model.CartItem;
import com.example.cart_service.service.CartItemService;
import com.example.cart_service.service.CartService;
import com.example.cart_service.service.ProductClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
                .findByCartIdAndProductId(cart, productId)
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
        List<CartItem> cartItems=cart.getItems();
        List<Long> productIds=cart.getItems().stream().map(CartItem::getProductId).toList();
        List<ProductResponse> products=productClientService.getAllProductsByIds(productIds);
        List<CartItemResponse> responsesItems=products.stream()
                .map(p->{
                  CartItem cartItem=cartItems.stream()
                          .filter(ci->ci.getProductId().equals(p.getProductId()))
                          .findFirst()
                          .orElseThrow();
                    return CartItemResponse.builder()
                            .productId(p.getProductId())
                            .productName(p.getProductName())
                            .price(p.getPrice())
                            .quantity(cartItem.getQuantity())
                            .build();
                }).toList();
        BigDecimal totalPrice = responsesItems.stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return CartResponse.builder()
                .userId(cart.getUserId())
                .items(responsesItems)
                .totalPrice(totalPrice)
                .build();
    }

    public String updateQuantity(Long userId,CartUpdateDto cartUpdateDto) {
        Cart cart=cartService.findCartByUserId(userId)
                .orElseThrow(()->new CartNotFound("cart is no found in the userId:"+userId));
        if(!(cartUpdateDto.getQuantity()>0)){
            throw new InvalidQuantity("Product quantity not be negative");
        }
        List<CartItem> cartItems=cart.getItems();
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(cartUpdateDto.getProductId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFound("Product not found, productId: "+ cartUpdateDto.getProductId()));
        cartItem.setQuantity(cartUpdateDto.getQuantity());
        cartItemService.save(cartItem);
        return "cartItem update successfully";
    }

    public String removeFromCartById(Long userId,Long productId) {
        Cart cart=cartService.findCartByUserId(userId)
                .orElseThrow(()->new CartNotFound("cart not found in the userId:"+userId));
        List<CartItem> cartItems=cart.getItems();
        CartItem cartItem=cartItems.stream().filter(item->item.getProductId().equals(productId))
                .findFirst().orElseThrow(()->new ProductNotFound("Product Not found in the cart productId:"+productId));
        cart.getItems().remove(cartItem);
        cartItemService.delete(cartItem);
        return "deleted successfully";
    }

    public String clearCart(Long userId) {
        Cart cart=cartService.findCartByUserId(userId)
                .orElseThrow(()->new CartNotFound("cart Not found userId:"+userId));
        cart.getItems().clear();
        return "cart cleared successfully";
    }
}
