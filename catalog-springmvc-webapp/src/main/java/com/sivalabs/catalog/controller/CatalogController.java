package com.sivalabs.catalog.controller;

import com.sivalabs.catalog.model.ProductResult;
import com.sivalabs.catalog.service.ProductServiceClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CatalogController {
    private final ProductServiceClient productServiceClient;

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductResult> products = productServiceClient.getProducts();
        model.addAttribute("products", products);
        return "products";
    }
}
