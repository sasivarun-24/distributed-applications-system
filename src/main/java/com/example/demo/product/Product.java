package com.example.demo.product;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private String size;
    private String color;  // lowercase 'c'

    // Constructor
    public Product(int id, String name, double price, String size, String color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;  // lowercase
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }  // Corrected getter
    public void setColor(String color) { this.color = color; } // Corrected setter

    public static List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "jacket", 49.99, "M", "black"));
        products.add(new Product(2, "Jeans", 39.99, "32", "Blue"));
        products.add(new Product(3, "winter shoes", 69.99, "10", "black"));
        products.add(new Product(4, "tee", 10.99, "M", "rose"));
        products.add(new Product(5, "Nike jordan", 89.99, "9", "Green"));
        return products;
    }
}
