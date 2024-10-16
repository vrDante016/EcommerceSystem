package com.produtos.Produtos.repository;

import com.produtos.Produtos.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRespository extends JpaRepository<Products, Long> {
    // aparecer menores preços e maiores preços
    List<Products> findByMaxPrice(Double price);
    List<Products> findByMinPrice(Double price);

    String findByName(String name);
}
