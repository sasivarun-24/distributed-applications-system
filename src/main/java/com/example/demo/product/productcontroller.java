package com.example.demo.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class productcontroller {

    private static final List<Product> products = List.of(
            new Product(1, "Black T-Shirt", 19.99, "M", "Black"),
            new Product(2, "Green Hoodie", 39.99, "L", "Green"),
            new Product(3, "Blue Jeans", 49.99, "32", "Blue"),
            new Product(4, "Red Jacket", 89.99, "M", "Red"),
            new Product(5, "White Sneakers", 59.99, "42", "White")
    );

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return products;
    }
}
