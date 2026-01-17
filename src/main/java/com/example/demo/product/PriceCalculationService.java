package com.example.demo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service for handling price calculations and rounding consistency across the
 * application.
 */
@Service
public class PriceCalculationService {

    @Value("${app.currency.default:EUR}")
    private String defaultCurrencyCode;

    @Value("${app.discount.percentage:10}")
    private double discountPercentage;

    private static final BigDecimal EUR_TO_USD_RATE = new BigDecimal("1.05");

    public Currency getDefaultCurrency() {
        return Currency.valueOf(defaultCurrencyCode);
    }

    public BigDecimal roundPrice(BigDecimal price) {
        if (price == null) {
            return BigDecimal.ZERO;
        }
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal convertPrice(BigDecimal price, Currency from, Currency to) {
        if (price == null)
            return BigDecimal.ZERO;
        if (from == to)
            return roundPrice(price);

        BigDecimal rate = BigDecimal.ONE;
        if (from == Currency.EUR && to == Currency.USD) {
            rate = EUR_TO_USD_RATE;
        } else if (from == Currency.USD && to == Currency.EUR) {
            rate = BigDecimal.ONE.divide(EUR_TO_USD_RATE, 4, RoundingMode.HALF_UP);
        }

        return roundPrice(price.multiply(rate));
    }

    public BigDecimal calculateVoucherDiscount(BigDecimal price) {
        if (price == null)
            return BigDecimal.ZERO;
        BigDecimal percentage = BigDecimal.valueOf(discountPercentage);
        BigDecimal discount = price.multiply(percentage).divide(BigDecimal.valueOf(100));
        return roundPrice(discount);
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
