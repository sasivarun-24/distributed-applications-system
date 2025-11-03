package com.example.demo.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public class ProductService {
    private static final List<Product> products = Product.getSampleProducts();

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(int id) {
        return products.stream().filter(a->a.getId()==id).findFirst();
    }

    public List<Product> filterProducts(String name, String size) {
        return products.stream()
                .filter(p -> (name == null || p.getName().equalsIgnoreCase(name))
                        && (size == null || p.getSize().equalsIgnoreCase(size)))
                .collect(Collectors.toList());
    }
}
