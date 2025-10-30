package com.example.demo.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Optional<Product> product = products.stream().filter(a->a.getId()==id).findFirst();;
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/products/filter")
    public List<Product> getProductByFilter(@RequestParam(required = false) String name, @RequestParam(required = false) String size) {
        return  products.stream()
                .filter(p -> (name == null || p.getName().equalsIgnoreCase(name))
                        && (size == null || p.getSize().equalsIgnoreCase(size)))
                .collect(Collectors.toList());
    }

}
