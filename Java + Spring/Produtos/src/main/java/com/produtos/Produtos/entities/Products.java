package com.produtos.Produtos.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productName", nullable = false, length = 70)
    private String productName;
    @Column(name = "priceProduct", nullable = false, length = 23)
    private Double priceProduct;
    @Column(name = "description", nullable = true, length = 100)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Products(){}

    public Products(String productName, Double priceProduct, String description, Category category) {
        this.productName = productName;
        this.priceProduct = priceProduct;
        this.description = description;
        this.category = category;
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

    public void setPriceProduct(Double priceName) {
        this.priceProduct = priceProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(id, products.id) && Objects.equals(productName, products.productName) && Objects.equals(priceProduct, products.priceProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, priceProduct);
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
