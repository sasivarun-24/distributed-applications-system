package com.example.demo.product;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    // Map of productId to stock count
    private Map<Integer, Integer> inventory = new HashMap<>();

    public InventoryService() {
        // Hardcoded stock for your example products
        inventory.put(1, 5); // productId 1
        inventory.put(2, 10); // productId 2
        inventory.put(3, 0); // productId 3
        // Add more productId-stock pairs as needed
    }

    public int getStockForProductId(int productId) {
        return inventory.getOrDefault(productId, 0);
    }

    public void reduceStockForProductId(int productId) {
        int stock = getStockForProductId(productId);
        if (stock > 0) {
            inventory.put(productId, stock - 1);
        }
    }
}
