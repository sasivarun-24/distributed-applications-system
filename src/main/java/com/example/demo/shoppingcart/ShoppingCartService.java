package com.example.demo.shoppingcart;

import com.example.demo.product.Product;
import com.example.demo.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private final ShoppingCart cart;

    @Autowired
    private ProductService productService;

    public ShoppingCartService() {
        this.cart = new ShoppingCart();
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addProductById(Long productId) {
        productService.getProductById(productId).ifPresent(p -> {
            cart.products.put(p, cart.products.getOrDefault(p, 0) + 1);
        });
    }

    public void removeProductById(Long productId) {
        productService.getProductById(productId).ifPresent(p -> {
            cart.products.remove(p);
        });
    }
}
