package com.emart.ecommerce.controller;

import com.emart.ecommerce.repository.RoleRepository;
import com.emart.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        int a = 5;
        return "login";
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }
}
