package com.produtos.Produtos.controller;

import com.produtos.Produtos.DTO.CategoryDTO;
import com.produtos.Produtos.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "category")
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getFindAll(){
        return categoryService.listAllCategories();
    }


}
