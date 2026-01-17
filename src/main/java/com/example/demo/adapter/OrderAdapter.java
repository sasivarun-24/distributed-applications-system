package com.example.demo.adapter;

import com.example.demo.email.EmailService;
import com.example.demo.facade.OrderFacade;
import com.example.demo.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OrderAdapter {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private EmailService emailService;

    public Order finalizeOrder(BigDecimal totalPrice) {
        Order order = orderFacade.finalizeOrder(totalPrice);
        emailService.sendEmail(order.getUserId());
        return order;
    }
}
