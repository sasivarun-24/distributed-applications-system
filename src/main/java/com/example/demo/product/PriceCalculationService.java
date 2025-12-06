package com.example.demo.product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

/**
 * Service for handling price calculations and rounding consistency across the
 * application.
 */
@Service
public class PriceCalculationService {

    /**
     * Rounds a price to 2 decimal places using RoundingMode.HALF_UP.
     *
     * @param price the price to round
     * @return the rounded price
     */
    public BigDecimal roundPrice(BigDecimal price) {
        if (price == null) {
            return BigDecimal.ZERO;
        }
        return price.setScale(2, RoundingMode.HALF_UP);
    }
}
