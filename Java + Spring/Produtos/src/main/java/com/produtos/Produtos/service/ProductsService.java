package com.produtos.Produtos.service;

import com.produtos.Produtos.entities.Category;
import com.produtos.Produtos.entities.Products;
import com.produtos.Produtos.exceptions.ProductNotFoundException;
import com.produtos.Produtos.DTO.ProductsDTO;
import com.produtos.Produtos.repository.CategoryRespository;
import com.produtos.Produtos.repository.ProductsRespository;
import com.produtos.Produtos.service.utils.GenericConverter;
import com.produtos.Produtos.service.utils.ProductsUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    private ProductsRespository productsRespository;

    @Autowired
    private CategoryRespository categoryRespository;
    @Autowired
    private ProductsUtil productsUtil ;
    @Autowired
    private  GenericConverter genericConverter;

    public ProductsService(ProductsRespository productsRespository, ProductsUtil productsUtil, GenericConverter genericConverter) {
        this.productsRespository = productsRespository;
        this.productsUtil = productsUtil;
        this.genericConverter = genericConverter;
    }

    //utilizando stream api para deixar o programa mais limpo e facil de entender
    public List<ProductsDTO> findAll(){
        return productsRespository.
                findAll().
                stream().
                map(products -> genericConverter.
                        convertToDTO(products, ProductsDTO.class)).collect(Collectors.toList());
    }

    //buscar por id
    public Optional<ProductsDTO> findById(Long id) throws ProductNotFoundException {
        System.out.println("Buscando produto com ID: " + id);
        return Optional.ofNullable(productsRespository.findById(id)
                .map(product -> {
                    System.out.println("Produto encontrado: " + product);
                    return genericConverter.convertToDTO(product, ProductsDTO.class);
                })
                .orElseThrow(() -> {
                    System.err.println("Produto não encontrado com ID: " + id);
                    return new ProductNotFoundException("Produto não encontrado com ID: " + id);
                }));
    }
    //adicionar novo produto
    public ProductsDTO addProduct(ProductsDTO productsDTO){
        Category category = categoryRespository.findById(productsDTO.getCategoryDTO().getId()).
                orElseGet(() -> {
                    Category newCategory = genericConverter.convertToEntity(productsDTO.getCategoryDTO(), Category.class);
                    return categoryRespository.save(newCategory);
                });
        Products product = genericConverter.convertToEntity(productsDTO, Products.class);
        product.setCategory(category);

        productsRespository.save(product);

        return genericConverter.convertToDTO(product, ProductsDTO.class);
    }

    //atualizar produtos
    public ProductsDTO updateProducts(Long id, ProductsDTO productsDTO){
        Products existingProducts = productsRespository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        genericConverter.convertToEntity(productsDTO, Products.class);
        productsRespository.save(existingProducts);
        return genericConverter.convertToDTO(existingProducts, ProductsDTO.class);
    }
    public void deleteProduct(Long id){
        if(!productsRespository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado");
        }
        productsRespository.deleteById(id);
    }

    public List<ProductsDTO>findByName(String name){
        return productsRespository.findByProductName(name).stream().map(products -> genericConverter.convertToDTO(products, ProductsDTO.class)).collect(Collectors.toList());
    }


    //procurar por menor preço
    public List<ProductsDTO> findByMinPriceOrEqual(Double price)  {
        ProductsUtil.validatePrice(price);
        List<Products> products = productsRespository.findByPriceProductLessThanEqual(price);
        ProductsUtil.productExistList(products);
        return products.stream().map(products1 -> genericConverter.convertToDTO(products1, ProductsDTO.class)).collect(Collectors.toList());
    }

    //procurar pelo maior preço
    public List<ProductsDTO> findByMaxPriceOrEqual(Double price)  {
        ProductsUtil.validatePrice(price);
        List<Products> products = productsRespository.findByPriceProductGreaterThanEqual(price);
        ProductsUtil.productExistList(products);
        return products.stream().map(products1 -> genericConverter.convertToDTO(products1, ProductsDTO.class)).collect(Collectors.toList());
    }





}
