package com.produtos.Produtos.repository;

import com.produtos.Produtos.entities.Category;
import com.produtos.Produtos.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRespository extends JpaRepository<Products, Long> {
    // aparecer menores preços e maiores preços
    List<Products> findByProductName(String productName);

    // Preços menores ou iguais
    List<Products> findByPriceProductLessThanEqual(Double priceProduct);

    // Preços maiores ou iguais
    List<Products> findByPriceProductGreaterThanEqual(Double priceProduct);

    List<Products> findByCategory(Category category);
}
