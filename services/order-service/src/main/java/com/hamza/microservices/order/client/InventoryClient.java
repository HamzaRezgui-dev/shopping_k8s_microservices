package com.hamza.microservices.order.client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/v1/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean IsInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    @PostExchange("/api/v1/inventory/deduct")
    void deductInventory(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallbackMethod(String skuCode, Exception throwable) {
        log.info("Fallback method for inventory service called, skuCode: {}, failure reason: {}", skuCode, throwable.getMessage());
        return false;
    }

}
