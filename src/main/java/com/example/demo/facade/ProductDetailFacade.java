package com.example.demo.facade;

import com.example.demo.product.Product;
import com.example.demo.product.ProductDetailDTO;
import com.example.demo.product.ProductService;
import com.example.demo.product.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductDetailFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    public ProductDetailDTO getProductDetailById(int productId) {
        Optional<Product> productOpt = productService.getProductById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            int stock = inventoryService.getStockForProductId(productId);
            return new ProductDetailDTO(product, stock);
        }
        return null;
    }
}
