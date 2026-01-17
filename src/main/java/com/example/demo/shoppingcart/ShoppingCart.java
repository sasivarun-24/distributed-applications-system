package com.example.demo.shoppingcart;

import com.example.demo.product.Product;
import com.example.demo.product.Currency;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // Map for product to quantity
    public Map<Product, Integer> products = new HashMap<>();

    private boolean voucherApplied = false;
    private Currency currency;

    public boolean isVoucherApplied() {
        return voucherApplied;
    }

    public void setVoucherApplied(boolean voucherApplied) {
        this.voucherApplied = voucherApplied;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
