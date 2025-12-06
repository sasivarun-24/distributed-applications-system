package com.example.demo.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/products/filter")
    public List<Product> getProductByFilter(@RequestParam(required = false) String name,
            @RequestParam(required = false) String size) {
        return productService.filterProducts(name, size);
    }

    @PostMapping("/products/create")
    public ResponseEntity<String> createProduct(@RequestParam String name,
            @RequestParam double price,
            @RequestParam String size,
            @RequestParam String color) {
        Product newProduct = new Product(name, java.math.BigDecimal.valueOf(price), size, color);
        boolean check = productService.checkDuplicate(newProduct); // This now checks/saves
        if (check) {
            return ResponseEntity.ok("Product added successfully!");
        } else {
            return ResponseEntity.badRequest().body("Duplicate product not added!");
        }
    }

    @PostMapping("/products/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        // Ensure ID is null for new products to avoid update logic override if
        // accidental ID sent
        product.setId(null);
        boolean added = productService.checkDuplicate(product);
        if (added) {
            return ResponseEntity.ok("Product added successfully!");
        } else {
            return ResponseEntity.badRequest().body("Duplicate product not added!");
        }
    }

    // Key update: returns true/false on existence
    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProductById(id);
        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Product not found!");
        }
    }

    @PutMapping("/products/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }
}
