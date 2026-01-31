package com.example.demo.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saas")
@Tag(name = "SaaS Catalog", description = "Endpoints for the SaaS Product Catalog")
public class SaaSCatalogController {

    private final ProductService productService;

    @Autowired
    public SaaSCatalogController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get all products", description = "Returns a list of all products in the catalog.")
    @GetMapping("/catalog")
    public List<Product> getCatalog() {
        return productService.getAllProducts();
    }
}
