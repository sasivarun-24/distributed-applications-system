package com.example.demo.facade;

import com.example.demo.order.Order;
import com.example.demo.order.OrderService;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OrderFacade {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    public Order finalizeOrder(BigDecimal totalPrice) {
        String userId = userService.getUserId();
        return orderService.finalizeOrder(totalPrice, userId);
    }
}
