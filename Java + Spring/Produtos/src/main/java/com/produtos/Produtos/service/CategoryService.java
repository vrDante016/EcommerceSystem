package com.produtos.Produtos.service;

import com.produtos.Produtos.DTO.CategoryDTO;
import com.produtos.Produtos.DTO.ProductsDTO;
import com.produtos.Produtos.entities.Category;
import com.produtos.Produtos.entities.Products;
import com.produtos.Produtos.exceptions.CategoryNotFoundException;
import com.produtos.Produtos.repository.CategoryRespository;
import com.produtos.Produtos.repository.ProductsRespository;
import com.produtos.Produtos.service.utils.GenericConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRespository categoryRespository;
    @Autowired
    private ProductsRespository productsRespository;

    private GenericConverter genericConverter;

    public CategoryService(CategoryRespository categoryRespository, GenericConverter genericConverter) {
        this.categoryRespository = categoryRespository;
        this.genericConverter = genericConverter;
    }

    public List<CategoryDTO> listAllCategories(){
        return categoryRespository.findAll().stream().map(category-> genericConverter.
                convertToDTO(category, CategoryDTO.class)).collect(Collectors.toList());

    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO){
        Category category= genericConverter.convertToEntity(categoryDTO, Category.class);
        categoryRespository.save(category);
        return genericConverter.convertToDTO(category, CategoryDTO.class);

    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO){
        Category category = categoryRespository.findById(id).
                orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
        genericConverter.convertToEntity(categoryDTO, Category.class);
        categoryRespository.save(category);
        return genericConverter.convertToDTO(category, CategoryDTO.class);
    }

    public void removeCategory(Long id){
        if(!categoryRespository.existsById(id)){
            throw new CategoryNotFoundException("categoria não encontrado");
        }
        categoryRespository.deleteById(id);
    }

    public List<ProductsDTO> getProductNyCategory(CategoryDTO categoryDTO){
        if(categoryDTO == null || categoryDTO.getId() == null){
            throw new CategoryNotFoundException("Categoria não encontrada");
        }

        Category category = categoryRespository.findById(categoryDTO.getId()).
                orElseThrow(() -> new CategoryNotFoundException("Categoria não econtrada"));

        List<Products> products = productsRespository.findByCategory(category);

        return products.stream().map(product -> genericConverter.convertToDTO(product, ProductsDTO.class)).collect(Collectors.toList());
    }




}
