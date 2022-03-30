package com.emart.ecommerce.controller;

import com.emart.ecommerce.dto.ProductDTO;
import com.emart.ecommerce.model.Category;
import com.emart.ecommerce.model.Product;
import com.emart.ecommerce.service.CategoryService;
import com.emart.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin/products")
    public String showAll(Model model) {
        List<Product> productList = productService.getAll();
        List<Category> categoryList = categoryService.getAll();

        model.addAttribute("products", productList);
        model.addAttribute("categories", categoryList);
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProductGet(Model model) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setImageName("product-default.png");
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", categoryService.getAll());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String addProductPost(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName") String imgName) {

        Product product = productService.mapDtoToObj(productDTO);

        if(!file.isEmpty()) {
            String imageUuid = productService.uploadProductImage(file);
            product.setImageName(imageUuid);
        }
        else if(!imgName.isEmpty()) {
            product.setImageName(imgName);
        }

        productService.add(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Boolean response = productService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Deleted product");
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/update/{id}")
    public String updateProductGet(@PathVariable Long id, Model model) {
        ProductDTO productDTO = productService.mapProductIntoDTO(id);
        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categories", categoryService.getAll());
        return "productsAdd";
    }
}
