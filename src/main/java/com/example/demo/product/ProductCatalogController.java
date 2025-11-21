package com.example.demo.product;

import com.example.demo.facade.ProductDetailFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mvc-api/product")
public class ProductCatalogController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailFacade productDetailFacade;

    @GetMapping
    public String catalog(@RequestParam(required = false, defaultValue = "false") boolean edit, Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("edit", edit);
        return "catalog";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        ProductDetailDTO dto = productDetailFacade.getProductDetailById(id);
        model.addAttribute("productDetailDTO", dto);
        return "detail";
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        return "redirect:/mvc-api/product?edit=true";
    }
}
