package com.example.demo.shoppingcart;

import com.example.demo.facade.AddToCartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShoppingCartController {

    @Autowired
    private AddToCartFacade addToCartFacade;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        ShoppingCart cart = addToCartFacade.getCart(); // Use facade proxy, not cartService directly
        double total = cart.products.entrySet()
                .stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable Long id) {
        addToCartFacade.addToCart(id); // Only call facade for add logic
        return "redirect:/cart";
    }
}
