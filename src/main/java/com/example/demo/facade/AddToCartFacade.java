package com.example.demo.facade;

import com.example.demo.product.InventoryService;
import com.example.demo.shoppingcart.ShoppingCartService;
import com.example.demo.shoppingcart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddToCartFacade {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private InventoryService inventoryService;

    // Add to cart - only if product is in stock
    public ShoppingCart addToCart(int productId) {
        int stock = inventoryService.getStockForProductId(productId);
        if (stock > 0) {
            cartService.addProductById(productId);
            inventoryService.reduceStockForProductId(productId);
        }
        // Always return current cart state, whether add succeeded or not
        return cartService.getCart();
    }

    // Proxy method so Controller does not access CartService directly
    public ShoppingCart getCart() {
        return cartService.getCart();
    }
}
