package com.example.order_service.mapper;

import com.example.order_service.dto.CartItemResponse;
import com.example.order_service.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderItem OrderItemResponseToOrderItem(CartItemResponse orderItemResponse){
        return OrderItem.builder().productId(orderItemResponse.getProductId())
                .quantity(orderItemResponse.getQuantity()).price(orderItemResponse.getPrice()).build();
    }
}
