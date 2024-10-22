package com.produtos.Produtos.controller;

import com.produtos.Produtos.DTO.CategoryDTO;
import com.produtos.Produtos.DTO.ProductsDTO;
import com.produtos.Produtos.exceptions.CategoryNotFoundException;
import com.produtos.Produtos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getFindAll(){
        return categoryService.listAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> gitFindById(@PathVariable Long id){
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> creatCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductsDTO>> getProductsByCategorys(@RequestBody CategoryDTO categoryDTO){
        List<ProductsDTO> productsDTO = categoryService.getProductsInCategory(categoryDTO);
        return ResponseEntity.ok().body(productsDTO);
    }



}
