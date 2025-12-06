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
    public String catalog(@RequestParam(required = false, defaultValue = "false") boolean edit,
            @RequestParam(required = false) String color,
            Model model) {
        if (color != null && !color.isEmpty()) {
            model.addAttribute("products", productService.getProductsByColor(color));
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }
        model.addAttribute("edit", edit);
        return "catalog";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductDetailDTO dto = productDetailFacade.getProductDetailById(id);
        model.addAttribute("productDetailDTO", dto);
        return "detail";
    }

    @GetMapping("/catalog-paginated")
    public String catalogPaginated(
            @org.springframework.data.web.PageableDefault(size = 3) org.springframework.data.domain.Pageable pageable,
            Model model) {
        org.springframework.data.domain.Page<com.example.demo.product.Product> page = productService
                .getProducts(pageable);
        model.addAttribute("products", page.getContent());
        model.addAttribute("page", page);
        return "catalog-paginated";
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/mvc-api/product?edit=true";
    }
}
