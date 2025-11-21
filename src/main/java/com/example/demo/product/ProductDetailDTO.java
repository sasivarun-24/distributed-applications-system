package com.example.demo.product;

public class ProductDetailDTO {
    private Product product;
    private int stock;
    private boolean isSoldOut;

    public ProductDetailDTO(Product product, int stock) {
        this.product = product;
        this.stock = stock;
        this.isSoldOut = (stock == 0);
    }

    public Product getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }

    public boolean getIsSoldOut() {
        return isSoldOut;
    }
}
