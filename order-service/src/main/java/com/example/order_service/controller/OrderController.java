package com.example.order_service.controller;

import com.example.order_service.dto.CartResponse;
import com.example.order_service.dto.OrderDto;
import com.example.order_service.facade.OrderFacade;
import com.example.order_service.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;
    @PostMapping("/place-order")
    public String placeOrder(@RequestHeader("X-USER-ID") Long userId){
        return orderFacade.placeOrder(userId);
    }
    @GetMapping("/view")
    public List<OrderDto> viewOrder(@RequestHeader("X-USER-ID") Long userId){
        return orderFacade.viewOrder(userId);
    }
}
