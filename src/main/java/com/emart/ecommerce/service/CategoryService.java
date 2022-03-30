package com.emart.ecommerce.service;

import com.emart.ecommerce.model.Category;
import com.emart.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public boolean addCategory(Category category) {
        Category response = categoryRepository.save(category);
        return true;
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findCategoryById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category;
    }
}
