package com.emart.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "adminHome";
    }

    @GetMapping("/shop")
    public String shop(Model model) {

        return "shop/productList";
    }
}
