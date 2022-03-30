package com.emart.ecommerce.controller;

import com.emart.ecommerce.model.Category;
import com.emart.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;

//    @GetMapping("/home")
//    public String home() {
//        return "adminHome";
//    }

    @GetMapping("/admin")
    public String admin() {
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String categories(Model model) {
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categories", categoryList);
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        if(categoryService.addCategory(category) ) {
            redirectAttributes.addFlashAttribute("message", "Category added successfully");
        }
        else{
            redirectAttributes.addFlashAttribute("message", "Failed to add category");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.findCategoryById(id);

        if(category.isPresent()) {
            model.addAttribute("category",category);
            return "categoriesAdd";
        }

        return "404";
    }
}
