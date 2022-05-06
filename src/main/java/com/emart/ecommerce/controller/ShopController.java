package com.emart.ecommerce.controller;

import com.emart.ecommerce.model.Category;
import com.emart.ecommerce.model.Product;
import com.emart.ecommerce.service.CategoryService;
import com.emart.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "adminHome";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        List<Category> categories = categoryService.getAll();
        List<Product> products = productService.getAll();

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "shop/productList";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id) {
        List<Category> categories = categoryService.getAll();
        List<Product> products = productService.getProductsByCategoryId(id);

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "shop/productList";
    }
}
