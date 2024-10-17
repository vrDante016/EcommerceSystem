package com.produtos.Produtos.repository;

import com.produtos.Produtos.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<Category, Long> {


}
