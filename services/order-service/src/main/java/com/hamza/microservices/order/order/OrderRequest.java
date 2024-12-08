package com.hamza.microservices.order.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank(message = "Order number is required")
        String skuCode,
        @NotNull(message = "Quantity is required")
        @Positive(message = "Quantity must be greater than zero")
        Integer quantity,
        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than zero")
        BigDecimal price
) {
}
