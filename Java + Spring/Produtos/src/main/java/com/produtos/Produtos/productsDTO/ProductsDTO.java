package com.produtos.Produtos.productsDTO;

public class ProductsDTO {

    private Long id;
    private String productName;

    private Double priceProduct;

    public ProductsDTO(){}

    public ProductsDTO(Long id, String productName, Double priceProduct) {
        this.id = id;
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

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }
}
