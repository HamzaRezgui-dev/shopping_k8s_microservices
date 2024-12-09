package com.hamza.microservices.inventory.inventory;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.IsInStock(skuCode, quantity);
    }

    @PostMapping("/deduct")
    @ResponseStatus(HttpStatus.OK)
    public void deductInventory(@RequestParam String skuCode, @RequestParam Integer quantity) {
        inventoryService.deductQuantity(skuCode, quantity);
    }
}
