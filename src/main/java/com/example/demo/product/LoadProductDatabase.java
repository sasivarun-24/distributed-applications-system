package com.example.demo.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Configuration
class LoadProductDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadProductDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository, JdbcTemplate jdbcTemplate) {
        return args -> {
            // 1. Cleanup duplicates if any exist from previous runs
            List<Product> allProducts = repository.findAll();
            java.util.Set<String> uniqueKeys = new java.util.HashSet<>();
            List<Long> idsToDelete = new java.util.ArrayList<>();

            for (Product p : allProducts) {
                // Remove Sunglasses as requested
                if ("Sunglasses".equalsIgnoreCase(p.getName())) {
                    idsToDelete.add(p.getId());
                    continue;
                }

                // strict duplicate check: same Name, Size, Color
                String key = p.getName() + "|" + p.getSize() + "|" + p.getColor();
                if (uniqueKeys.contains(key)) {
                    idsToDelete.add(p.getId());
                } else {
                    uniqueKeys.add(key);
                }
            }

            if (!idsToDelete.isEmpty()) {
                log.info("Found " + idsToDelete.size() + " duplicate products. Removing them...");
                repository.deleteAllById(idsToDelete);
                log.info("Duplicates removed.");
            }

            // 2. Conditional Seeding
            // Check if we already have enough products (e.g., >= 10)
            long count = repository.count();
            if (count >= 10) {
                log.info("Database already contains " + count + " products. Skipping initialization.");
                return;
            }

            log.info("Database contains " + count + " products. Initializing sample data...");

            // Add sample products if not enough are present
            // We are using hardcoded values here as requested
            saveProductIfNotExists(repository,
                    new Product("Jacket", new java.math.BigDecimal("49.99"), "M", "Black", Category.SALE.name()));
            saveProductIfNotExists(repository,
                    new Product("Jeans", new java.math.BigDecimal("39.99"), "32", "Blue", Category.STANDARD.name()));
            saveProductIfNotExists(repository,
                    new Product("Winter Shoes", new java.math.BigDecimal("69.99"), "10", "Black",
                            Category.STANDARD.name()));
            saveProductIfNotExists(repository,
                    new Product("Tee", new java.math.BigDecimal("10.99"), "M", "Rose", Category.SALE.name()));
            saveProductIfNotExists(repository,
                    new Product("Nike Jordan", new java.math.BigDecimal("89.99"), "9", "Green",
                            Category.STANDARD.name()));
            saveProductIfNotExists(repository,
                    new Product("Scarf", new java.math.BigDecimal("14.99"), "One Size", "Red", Category.SALE.name()));
            saveProductIfNotExists(repository,
                    new Product("Hat", new java.math.BigDecimal("19.99"), "L", "Black", Category.STANDARD.name()));
            saveProductIfNotExists(repository,
                    new Product("Socks", new java.math.BigDecimal("5.99"), "40-42", "White", Category.SALE.name()));
            saveProductIfNotExists(repository,
                    new Product("Gloves", new java.math.BigDecimal("12.99"), "M", "Black", Category.STANDARD.name()));
            saveProductIfNotExists(repository,
                    new Product("Belt", new java.math.BigDecimal("24.99"), "L", "Brown", Category.SALE.name()));

            log.info("Initialization complete. Current count: " + repository.count());
        };
    }

    private void saveProductIfNotExists(ProductRepository repository, Product product) {
        // Since use standard save, we just save.
        // In a real app we might check by name/attributes to avoid dups if IDs differ,
        // but for this exercise we just save new instances if overall count is low.
        repository.save(product);
        log.info("Preloaded " + product.getName());
    }
}
