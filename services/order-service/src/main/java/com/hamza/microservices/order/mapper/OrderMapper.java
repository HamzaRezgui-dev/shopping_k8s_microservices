package com.hamza.microservices.order.mapper;

import com.hamza.microservices.order.dto.OrderRequest;
import com.hamza.microservices.order.dto.OrderResponse;
import com.hamza.microservices.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
            order.getId(),
            order.getOrderNumber(),
            order.getSkuCode(),
            order.getQuantity(),
            order.getPrice()
        );
    }

    public Order toOrder(OrderRequest request) {
        return new Order(
            UUID.randomUUID().toString(),
            request.skuCode(),
            request.price(),
            request.quantity()
        );
    }
}
