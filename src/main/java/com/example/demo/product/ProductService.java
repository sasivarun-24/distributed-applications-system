package com.example.demo.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
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
        // This method can remain but ideally should use repository specifications or
        // QBE.
        // Keeping it simple for now as per prompt focus on color.
        List<Product> all = productRepository.findAll();
        return all.stream()
                .filter(p -> (name == null || p.getName().equalsIgnoreCase(name))
                        && (size == null || p.getSize().equalsIgnoreCase(size)))
                .collect(Collectors.toList());
    }

    // Logic: if ID is null or 0, it's new. If repository logic is used, save works
    // for both.
    public boolean saveProduct(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Deprecated wrapper for backward compatibility if needed, or can be replaced
    // in Controller.
    // Keeping method signature to avoid breaking Controller if not updating it
    // right now,
    // but redirecting to saveProduct.
    public boolean checkDuplicate(Product product) {
        return saveProduct(product);
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
