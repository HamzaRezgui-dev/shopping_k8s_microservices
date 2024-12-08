package com.hamza.microservices.order.order;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,
        String skuCode,
        String orderNumber,
        BigDecimal price,
        Integer quantity
) {
}
