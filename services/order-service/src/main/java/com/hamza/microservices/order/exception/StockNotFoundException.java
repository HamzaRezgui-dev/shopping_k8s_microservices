package com.hamza.microservices.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StockNotFoundException extends RuntimeException {
    private final String message;
}
