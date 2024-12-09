package com.hamza.microservices.order.inventory;

public record InventoryResponse(
        String skuCode,
        Integer quantity
) {
}
