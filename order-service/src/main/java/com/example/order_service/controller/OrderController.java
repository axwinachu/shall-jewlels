package com.example.order_service.controller;

import com.example.order_service.dto.OrderCreateEvent;
import com.example.order_service.dto.OrderDto;
import com.example.order_service.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderFacade orderFacade;
    @PostMapping("/place-order")
    public String placeOrder(@RequestHeader("X-USER-ID") Long userId, @RequestBody OrderCreateEvent orderRequest){
        return orderFacade.placeOrder(userId,orderRequest);
    }
    @GetMapping("/view")
    public List<OrderDto> viewOrder(@RequestHeader("X-USER-ID") Long userId){
        return orderFacade.viewOrder(userId);
    }
}
