package com.example.order_service.facade;

import com.example.order_service.dto.CartResponse;
import com.example.order_service.dto.OrderCreateEvent;
import com.example.order_service.dto.OrderDto;
import com.example.order_service.dto.OrderItemDto;
import com.example.order_service.enums.OrderStatus;
import com.example.order_service.enums.ResponseMessages;
import com.example.order_service.exception.CartISEmpty;
import com.example.order_service.exception.OrderNotFound;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderItem;
import com.example.order_service.service.CartClientService;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CartClientService cartClientService;
    private final KafkaTemplate<String, Map<String,Object>> kafkaTemplate;
    public String placeOrder(Long userId, OrderCreateEvent orderCreateEvent) {
        Map<String,Object> map=new HashMap<>();
        CartResponse cart=cartClientService.getCartByUserId(userId);
        if(cart.getItems().size()==0){
            throw new CartISEmpty(ResponseMessages.CART_IS_EMPTY.name());
        }

        Order order=Order.builder()
                .userId(userId)
                .totalAmount(cart.getTotalPrice())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();
        cart.getItems().forEach(item -> {
            OrderItem orderItem =
                    orderMapper.OrderItemResponseToOrderItem(item);
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
        });
        orderService.saveOrder(order);

        map.put("name",orderCreateEvent.getName());
        map.put("email",orderCreateEvent.getEmail());
        map.put("address",orderCreateEvent.getAddress());
        kafkaTemplate.send("my-order-3",map);

        return ResponseMessages.ORDER_PLACED_SUCCESSFULLY.name();

    }

    public List<OrderDto> viewOrder(Long userId) {

        List<Order> orders = orderService.findAllByUserId(userId);

        if (orders.isEmpty()) {
            throw new OrderNotFound(ResponseMessages.NO_PREVIOUS_ORDER_FOUND.name());
        }
        return orders.stream()
                .map(order -> OrderDto.builder()
                        .orderId(order.getId())
                        .totalPrice(order.getTotalAmount())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .items(order.getItems().stream().map(item -> OrderItemDto.builder()
                                                .id(item.getId())
                                                .quantity(item.getQuantity())
                                                .price(item.getPrice())
                                                .build()).toList()).build()).toList();
    }

}
