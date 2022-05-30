package com.emart.ecommerce.controller;

import com.emart.ecommerce.model.Product;
import com.emart.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable Long id) {
        Product product = productRepository.findById(id).get();

        String name = product.getName();
        return "<h1> name </h1>";
    }
}
