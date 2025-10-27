package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private double price;
    private String size;
    private String color;

    // Constructor
    public Product(int id, String name, double price, String size, String color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
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

//    public String getColor() { return color; }
//    public void setColor(String color) { this.color=color;}

    public static List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "T-Shirt", 19.99, "M", "Red"));
        products.add(new Product(2, "Jeans", 49.99, "L", "Blue"));
        products.add(new Product(3, "Sneakers", 69.99, "42", "White"));
        products.add(new Product(4, "Hat", 15.99, "One Size", "Black"));
        products.add(new Product(5, "Jacket", 89.99, "XL", "Green"));
        return products;
    }
}
