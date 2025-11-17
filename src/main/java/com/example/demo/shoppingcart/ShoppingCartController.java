package com.example.demo.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        ShoppingCart cart = cartService.getCart();
        double total = cart.products.entrySet()
                .stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable int id) {
        cartService.addProductById(id);
        return "redirect:/cart";
    }
}
