package com.example.demo.product;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    // Map of productId to stock count
    private Map<Integer, Integer> inventory = new HashMap<>();

    public InventoryService() {
        // Initialize stock for a range of product IDs to cover seeded data
        for (int i = 1; i <= 20; i++) {
            inventory.put(i, 10); // Default stock of 10 for all
        }
        // Set some items as out of stock for demonstration
        inventory.put(3, 0);
        inventory.put(7, 0);
        inventory.put(9, 0);
        inventory.put(14, 0);
    }

    public int getStockForProductId(int productId) {
        // Robust logic: Make roughly 2-3 products out of stock based on ID
        // This works even if IDs drift (e.g. 21, 22...)
        if (productId % 5 == 0 || productId % 5 == 3) {
            return 0;
        }
        // Default to 10 if not explicitly tracked
        return inventory.getOrDefault(productId, 10);
    }

    public void reduceStockForProductId(int productId) {
        int stock = getStockForProductId(productId);
        if (stock > 0) {
            inventory.put(productId, stock - 1);
        }
    }
}
