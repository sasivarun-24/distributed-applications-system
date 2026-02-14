package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExternalProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> fetchProductsFromExternalBackend() {
        String url = "http://localhost:8081/api/catalog";
        try {
            Product[] products = restTemplate.getForObject(url, Product[].class);
            if (products != null) {
                return Arrays.asList(products);
            }
        } catch (Exception e) {
            // Log error or handle gracefully
            System.err.println("Error fetching products from external backend: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
