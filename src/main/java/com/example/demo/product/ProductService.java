package com.example.demo.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service


public class ProductService {
    private static final List<Product> products = Product.getSampleProducts();

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(int id) {
        return products.stream().filter(a -> a.getId() == id).findFirst();
    }

    public List<Product> filterProducts(String name, String size) {
        return products.stream()
                .filter(p -> (name == null || p.getName().equalsIgnoreCase(name))
                        && (size == null || p.getSize().equalsIgnoreCase(size)))
                .collect(Collectors.toList());
    }

    public boolean checkDuplicate(Product product) {
        boolean exists = products.stream().anyMatch(a -> a.getId() == product.getId());
        if (!exists) {
            products.add(product);
            return true;
        }
        return false;
    }

    // Fixed: Returns true if actually deleted, false if ID not found
    public boolean deleteProductById(int id) {
        Optional<Product> match = products.stream().filter(a -> a.getId() == id).findFirst();
        if (match.isPresent()) {
            products.remove(match.get());
            return true;
        }
        return false;
    }

    public Product updateProduct(Product product) {
        Optional<Product> existingProduct = products.stream()
                .filter(a -> a.getId() == product.getId())
                .findFirst();
        if (existingProduct.isPresent()) {
            Product a = existingProduct.get();
            a.setName(product.getName());
            a.setPrice(product.getPrice());
            a.setSize(product.getSize());
            a.setColor(product.getColor());
            return a;
        }
        return null;
    }
}

