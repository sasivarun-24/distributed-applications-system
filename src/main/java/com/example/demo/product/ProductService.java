package com.example.demo.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByColor(String color) {
        return productRepository.findByColor(color);
    }

    public List<Product> filterProducts(String name, String size) {
        // For now, fetching all and filtering in memory or use repository if needed.
        // Prompt asks for color filtering specifically via repository.
        // This method can remain but ideally should use repository specifications or QBE.
        // Keeping it simple for now as per prompt focus on color.
        List<Product> all = productRepository.findAll();
        return all.stream()
                .filter(p -> (name == null || p.getName().equalsIgnoreCase(name))
                        && (size == null || p.getSize().equalsIgnoreCase(size)))
                .collect(Collectors.toList());
    }

    // Renamed/Refactored: checkDuplicate was used to add. Now using save.
    // Logic: if ID is null or 0, it's new. If repository logic is used, save works for both.
    public boolean checkDuplicate(Product product) {
        // In the old logic, it checked if ID exists.
        // For generated IDs, we just save.
        // However, the controller uses this to return success/fail.
        // We will assume success and save.
        // If we want to check duplicates by name/etc, we can do that.
        // The old "id" based check doesn't make sense for auto-increment.
        // Let's just save.
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Product updateProduct(Product product) {
        if (product.getId() != null && productRepository.existsById(product.getId())) {
             return productRepository.save(product);
        }
        return null;
    }
}


