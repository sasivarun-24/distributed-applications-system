package com.example.demo.product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review {
    private Long productId;
    private String productName;
    private String userName;
    private String reviewText;
    private String date;

    public Review() {
    }

    public Review(Long productId, String productName, String userName, String reviewText) {
        this.productId = productId;
        this.productName = productName;
        this.userName = userName;
        this.reviewText = reviewText;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
