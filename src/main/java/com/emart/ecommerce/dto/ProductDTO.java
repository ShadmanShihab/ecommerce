package com.emart.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String imageName;
    private double price;
    private double weight;
    private int categoryId;
}
