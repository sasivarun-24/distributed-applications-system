package com.example.demo.order;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OrderService {

    public Order finalizeOrder(BigDecimal totalPrice, String userId) {
        return new Order(totalPrice, userId);
    }
}
