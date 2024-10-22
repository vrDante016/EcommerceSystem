package com.produtos.Produtos.DTO;


import jakarta.persistence.*;

import java.util.Objects;


public class ProductsDTO {


    private Long id;

    private String productName;

    private Double priceProduct;

    private String description;

    private CategoryDTO categoryDTO;

    public ProductsDTO(){}

    public ProductsDTO(Long id, String productName, Double priceProduct, String description, CategoryDTO categoryDTO) {
        this.id = id;
        this.productName = productName;
        this.priceProduct = priceProduct;
        this.description = description;
        this.categoryDTO = categoryDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceName(Double priceName) {
        this.priceProduct = priceProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", priceProduct=" + priceProduct +
                ", description='" + description + '\'' +
                '}';
    }
}
