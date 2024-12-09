package com.hamza.microservices.order.order;

import com.hamza.microservices.order.client.InventoryClient;
import com.hamza.microservices.order.exception.StockNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        var isInStock = inventoryClient.IsInStock(orderRequest.skuCode(), orderRequest.quantity());

        if (isInStock) {
            inventoryClient.deductInventory(orderRequest.skuCode(), orderRequest.quantity());
            Order savedOrder = orderRepository.save(orderMapper.toOrder(orderRequest));
            return orderMapper.toOrderResponse(savedOrder);
        }
        else {
            throw new StockNotFoundException("Insufficient stock to fulfill the order");
        }

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
