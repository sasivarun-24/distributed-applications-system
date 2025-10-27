package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class ProductController {



    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return Product.getSampleProducts();
}
}