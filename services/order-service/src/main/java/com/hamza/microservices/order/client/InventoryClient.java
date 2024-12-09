package com.hamza.microservices.order.client;

import com.hamza.microservices.order.inventory.InventoryRequest;
import com.hamza.microservices.order.inventory.InventoryResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "inventory-service",
        url = "http://localhost:8070"
)
public interface InventoryClient {
    @GetMapping("/api/v1/inventory")
    boolean IsInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    @PostMapping("/api/v1/inventory")
    ResponseEntity<InventoryResponse> createInventory(@RequestBody @Valid InventoryRequest inventoryRequest);

    @PostMapping("/api/v1/inventory/deduct")
    void deductInventory(@RequestParam String skuCode, @RequestParam Integer quantity);
}
