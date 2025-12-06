package com.example.demo.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
class LoadProductDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadProductDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository, JdbcTemplate jdbcTemplate) {
        return args -> {

            boolean hasDrift = repository.count() > 0 && repository.findById(1L).isEmpty();
            boolean hasDuplicates = repository.count() > 15;

            if (hasDuplicates || hasDrift) {
                log.info("Evaluating database state: Drift=" + hasDrift + ", Duplicates=" + hasDuplicates);
                log.info("Resetting database to ensure clean state and ID sequence...");

                repository.deleteAll();

                try {
                    jdbcTemplate.execute("ALTER TABLE product ALTER COLUMN id RESTART WITH 1");
                    log.info("ID sequence reset to 1.");
                } catch (Exception e) {
                    log.error("Failed to reset ID sequence: " + e.getMessage());
                }
            }

            if (repository.count() == 0) {
                log.info("Preloading " + repository.save(new Product("Jacket", 49.99, "M", "Black")));
                log.info("Preloading " + repository.save(new Product("Jeans", 39.99, "32", "Blue")));
                log.info("Preloading " + repository.save(new Product("Winter Shoes", 69.99, "10", "Black")));
                log.info("Preloading " + repository.save(new Product("Tee", 10.99, "M", "Rose")));
                log.info("Preloading " + repository.save(new Product("Nike Jordan", 89.99, "9", "Green")));
                log.info("Preloading " + repository.save(new Product("Scarf", 14.99, "One Size", "Red")));
                log.info("Preloading " + repository.save(new Product("Hat", 19.99, "L", "Black")));
                log.info("Preloading " + repository.save(new Product("Socks", 5.99, "40-42", "White")));
                log.info("Preloading " + repository.save(new Product("Gloves", 12.99, "M", "Black")));
                log.info("Preloading " + repository.save(new Product("Belt", 24.99, "L", "Brown")));
            } else {
                log.info("Database already seeded with " + repository.count() + " products.");
            }
        };
    }
}
