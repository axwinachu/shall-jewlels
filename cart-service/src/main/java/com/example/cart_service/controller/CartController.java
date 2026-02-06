package com.example.cart_service.controller;

import com.example.cart_service.dto.CartResponse;
import com.example.cart_service.dto.CartUpdateDto;
import com.example.cart_service.facade.CartFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartFacade cartFacade;
    @PostMapping("/add/{productId}")
    public String addProductToCart(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long productId,@RequestParam Long Quantity){
       return cartFacade.addProductToCart(userId,productId,Quantity);
    }
    @GetMapping("/view")
    public CartResponse viewCart(@RequestHeader("X-USER-ID") Long userId){
        return cartFacade.viewCart(userId);
    }
    @PutMapping("/update")
    public String updateQuantity(@RequestHeader("X-USER-ID") Long userId,@RequestBody CartUpdateDto cartUpdateDto){
        return cartFacade.updateQuantity(userId,cartUpdateDto);
    }
    @DeleteMapping("/{productId}")
    public String removeFromCartById(@RequestHeader("X-USER-ID") Long userId,@PathVariable Long productId ){
        return cartFacade.removeFromCartById(userId,productId);
    }
    @DeleteMapping("/clear")
    public String clearCart(@RequestHeader("X-USER-ID") Long userId){
        return cartFacade.clearCart(userId);
    }

}
