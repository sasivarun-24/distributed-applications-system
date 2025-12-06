package com.example.demo.facade;

import com.example.demo.product.Product;
import com.example.demo.product.ProductDetailDTO;
import com.example.demo.product.ProductService;
import com.example.demo.product.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Facade providing simplified access to product details.
 * <p>
 * This component aggregates information from {@link ProductService} and
 * {@link InventoryService}
 * to provide a complete view of a product including its stock availability.
 * </p>
 */
@Service
public class ProductDetailFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    public ProductDetailDTO getProductDetailById(Long productId) {
        Optional<Product> productOpt = productService.getProductById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            // Inventory service might still expect int? Let's check or cast if needed.
            // Assuming for now we cast to int for inventory if it's not refactored yet, or
            // change inventory too.
            // For now, let's look at InventoryService signature.
            // But to fix compilation of THIS file, we need to match ProductService which
            // expects Long.
            int stock = inventoryService.getStockForProductId(productId.intValue());
            return new ProductDetailDTO(product, stock);
        }
        return null;
    }
}
