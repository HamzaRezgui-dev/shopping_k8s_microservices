package com.hamza.microservices.order.order;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getSkuCode(),
                order.getOrderNumber(),
                order.getPrice(),
                order.getQuantity()
        );
    }

    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .skuCode(orderRequest.skuCode())
                .orderNumber(UUID.randomUUID().toString())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();
    }
}
