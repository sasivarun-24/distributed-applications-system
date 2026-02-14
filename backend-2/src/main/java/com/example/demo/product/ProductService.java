package com.example.demo.product;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> fetchAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(101L, "Fancy T-Shirt", new BigDecimal("19.99"), "M", "Red"));
        products.add(new Product(102L, "Fancy Socks", new BigDecimal("9.99"), "L", "Blue"));
        products.add(new Product(103L, "Fancy Hat", new BigDecimal("25.00"), "One Size", "Black"));
        products.add(new Product(104L, "Fancy Scarf", new BigDecimal("15.50"), "One Size", "Green"));
        products.add(new Product(105L, "Fancy Gloves", new BigDecimal("12.99"), "M", "Yellow"));
        return products;
    }
}
