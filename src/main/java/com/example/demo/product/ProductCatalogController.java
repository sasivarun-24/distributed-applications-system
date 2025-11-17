package com.example.demo.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc-api/product")
public class ProductCatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String catalog(@RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("edit", edit);
        return "catalog";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        productService.getProductById(id).ifPresent(p -> model.addAttribute("product", p));
        return "detail";
    }

    // Non-RESTful delete endpoint
    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return "redirect:/mvc-api/product?edit=true";
    }
}
