package com.example.demo.facade;

import com.example.demo.product.InventoryService;
import com.example.demo.shoppingcart.ShoppingCartService;
import com.example.demo.shoppingcart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Facade managing the shopping cart operations.
 * <p>
 * This component acts as a higher-level interface to the
 * {@link ShoppingCartService} and {@link InventoryService},
 * ensuring that products are only added to the cart if they are in stock and
 * that stock is reserved accordingly.
 * </p>
 */
@Service
public class AddToCartFacade {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private InventoryService inventoryService;

    // Add to cart - only if product is in stock
    public ShoppingCart addToCart(Long productId) {
        int stock = inventoryService.getStockForProductId(productId.intValue());
        if (stock > 0) {
            cartService.addProductById(productId);
            inventoryService.reduceStockForProductId(productId.intValue());
        }
        // Always return current cart state, whether add succeeded or not
        return cartService.getCart();
    }

    // Proxy method so Controller does not access CartService directly
    public ShoppingCart getCart() {
        return cartService.getCart();
    }
}
