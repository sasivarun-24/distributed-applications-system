package com.example.demo.product;

public enum Currency {
    EUR("€"),
    USD("$");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
