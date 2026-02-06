package com.example.order_service.client;

import com.example.order_service.dto.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "cart-service")
public interface CartClient {
    @GetMapping("/cart/view")
    CartResponse getCartByUserId(@RequestHeader("X-USER-ID") Long userId);
}
