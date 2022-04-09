package com.emart.ecommerce.controller;

import com.emart.ecommerce.model.Role;
import com.emart.ecommerce.model.User;
import com.emart.ecommerce.repository.RoleRepository;
import com.emart.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
        return "login";
    }

    @RequestMapping("/login-success")
    public String loginSuccess(Model m) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (user.equals("ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/user/home";
        }
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(1).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(), user.getPassword());

        return "redirect:/";
    }
}
