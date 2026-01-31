package com.example.demo.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saas")
@Tag(name = "SaaS Catalog", description = "Endpoints for the SaaS Product Catalog")
public class SaaSCatalogController {

    private final ProductService productService;
    private final com.example.demo.config.TenantConfig tenantConfig;

    @Autowired
    public SaaSCatalogController(ProductService productService, com.example.demo.config.TenantConfig tenantConfig) {
        this.productService = productService;
        this.tenantConfig = tenantConfig;
    }

    @Operation(summary = "Get all products", description = "Returns a list of all products in the catalog filtered by Tenant ID.")
    @GetMapping("/catalog")
    public List<Product> getCatalog(@RequestHeader(value = "X-TENANT-ID", required = false) String tenantId) {
        if (tenantId != null && tenantConfig.getMapping().containsKey(tenantId)) {
            String category = tenantConfig.getMapping().get(tenantId);
            return productService.getProductsByCategory(category);
        }
        // Fallback or empty if no tenant/mapping found (though filter should catch
        // invalid tenants)
        return List.of();
    }
}
