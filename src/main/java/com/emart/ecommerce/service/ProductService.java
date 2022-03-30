package com.emart.ecommerce.service;

import com.emart.ecommerce.dto.ProductDTO;
import com.emart.ecommerce.model.Product;
import com.emart.ecommerce.repository.ProductRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryService categoryService;

    public String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        try {
            productRepository.save(product);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public Product mapDtoToObj(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.findCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setWeight(productDTO.getWeight());
        return product;
    }


    public String uploadProductImage(MultipartFile file) {
        String imageUuid = "";
        if(!file.isEmpty()) {
            try {
                imageUuid = file.getOriginalFilename();
                Path fileNameAndPath = Paths.get(uploadDir, imageUuid);
                Files.write(fileNameAndPath, file.getBytes());
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return imageUuid;
    }

    public Boolean delete(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public ProductDTO mapProductIntoDTO(Long id) {
        Product product = productRepository.getById(id);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageName(product.getImageName());

        return productDTO;
    }
}
