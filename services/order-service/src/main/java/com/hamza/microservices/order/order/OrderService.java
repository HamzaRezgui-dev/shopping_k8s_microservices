package com.hamza.microservices.order.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order savedOrder = orderRepository.save(orderMapper.toOrder(orderRequest));
        return orderMapper.toOrderResponse(savedOrder);
    }

    public OrderResponse getOrder(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderResponse> getOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
