package com.example.demo.shoppingcart;

import com.example.demo.facade.AddToCartFacade;
import com.example.demo.product.PriceCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.product.Currency;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;

@Controller
public class ShoppingCartController {

    @Autowired
    private AddToCartFacade addToCartFacade;

    @Autowired
    private PriceCalculationService priceCalculationService;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        ShoppingCart cart = addToCartFacade.getCart();

        if (cart.getCurrency() == null) {
            cart.setCurrency(priceCalculationService.getDefaultCurrency());
        }

        BigDecimal totalBase = cart.products.entrySet()
                .stream()
                .map(e -> e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discountAmount = BigDecimal.ZERO;
        if (cart.isVoucherApplied()) {
            discountAmount = priceCalculationService.calculateVoucherDiscount(totalBase);
        }
        BigDecimal totalAfterDiscount = totalBase.subtract(discountAmount);

        Currency userCurrency = cart.getCurrency();
        Currency baseCurrency = Currency.EUR;

        BigDecimal displayTotal = priceCalculationService.convertPrice(totalAfterDiscount, baseCurrency, userCurrency);

        model.addAttribute("cart", cart);
        model.addAttribute("total", displayTotal);
        model.addAttribute("currencySymbol", userCurrency.getSymbol());
        model.addAttribute("voucherApplied", cart.isVoucherApplied());
        model.addAttribute("discountPercentage", priceCalculationService.getDiscountPercentage());

        model.addAttribute("isEur", userCurrency == Currency.EUR);

        return "cart";
    }

    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable Long id) {
        addToCartFacade.addToCart(id); // Only call facade for add logic
        return "redirect:/cart";
    }

    @GetMapping("/cart/voucher")
    public String applyVoucher() {
        addToCartFacade.getCart().setVoucherApplied(true);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove-voucher")
    public String removeVoucher() {
        addToCartFacade.getCart().setVoucherApplied(false);
        return "redirect:/cart";
    }

    @GetMapping("/cart/currency")
    public String changeCurrency(@RequestParam("code") String code) {
        try {
            Currency c = Currency.valueOf(code);
            addToCartFacade.getCart().setCurrency(c);
        } catch (IllegalArgumentException e) {
        }
        return "redirect:/cart";
    }
}
