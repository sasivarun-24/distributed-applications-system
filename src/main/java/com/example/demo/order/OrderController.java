package com.example.demo.order;

import com.example.demo.adapter.OrderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class OrderController {

    @Autowired
    private OrderAdapter orderAdapter;

    @PostMapping("/checkout")
    public String checkout(@RequestParam("total") BigDecimal total, Model model) {
        Order order = orderAdapter.finalizeOrder(total);
        model.addAttribute("order", order);
        return "order-success";
    }
}
