package com.example.demo.order;

import java.math.BigDecimal;

public class Order {
    private BigDecimal price;
    private String userId;

    public Order(BigDecimal price, String userId) {
        this.price = price;
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getUserId() {
        return userId;
    }
}
