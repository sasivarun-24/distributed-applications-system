package com.example.demo.shoppingcart;

import com.example.demo.facade.AddToCartFacade;
import com.example.demo.product.PriceCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.math.BigDecimal;

@Controller
public class ShoppingCartController {

    @Autowired
    private AddToCartFacade addToCartFacade;

    @Autowired
    private PriceCalculationService priceCalculationService;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        ShoppingCart cart = addToCartFacade.getCart(); // Use facade proxy, not cartService directly
        BigDecimal total = cart.products.entrySet()
                .stream()
                .map(e -> e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        total = priceCalculationService.roundPrice(total);

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
