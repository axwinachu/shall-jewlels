package com.example.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateEvent {
    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;
    private List<OrderItemDto> items;
}
