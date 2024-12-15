package com.hamza.microservices.order.client;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface InventoryClient {
    @GetExchange("/api/v1/inventory")
    boolean IsInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    @PostExchange("/api/v1/inventory/deduct")
    void deductInventory(@RequestParam String skuCode, @RequestParam Integer quantity);
}
