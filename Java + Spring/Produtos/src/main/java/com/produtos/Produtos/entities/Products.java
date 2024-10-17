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

    public Products(){}

    public Products( String productName, Double priceProduct) {

        this.productName = productName;
        this.priceProduct = priceProduct;
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
}
