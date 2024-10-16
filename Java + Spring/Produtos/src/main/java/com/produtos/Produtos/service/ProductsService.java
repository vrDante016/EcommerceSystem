package com.produtos.Produtos.service;

import com.produtos.Produtos.entities.Products;
import com.produtos.Produtos.exceptions.PriceNotValidException;
import com.produtos.Produtos.exceptions.ProductNofFoundException;
import com.produtos.Produtos.productsDTO.ProductsDTO;
import com.produtos.Produtos.repository.ProductsRespository;
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

    private final ModelMapper modelMapper = new ModelMapper();


    //utilizando stream api para deixar o programa mais limpo e facil de entender
    public List<ProductsDTO> findAll(){
        return productsRespository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //buscar por id
    public Optional<ProductsDTO> findById(Long id) {

        return Optional.ofNullable(productsRespository.findById(id).map(this::convertToDto).orElseThrow(() -> new ProductNofFoundException("Produto não encontrado" + id)));
    }
    //adicionar novo produto
    public ProductsDTO addProduct(ProductsDTO productsDTO){
        Products products = modelMapper.map(productsDTO, Products.class);
        productsRespository.save(products);
        return convertToDto(products);

    }

    //atualizar produtos
    public ProductsDTO updateProducts(Long id, ProductsDTO productsDTO){
        Products products = productsRespository.findById(id).orElseThrow(() -> new ProductNofFoundException("Produto não encontrado"));

        modelMapper.map(productsDTO, products);
        productsRespository.save(products);

        return convertToDto(products);
    }
    public void deleteProduct(Long id){
        productsRespository.deleteById(id);
    }

    public ProductsDTO findByName(String name){

    }


    //procurar por menor preço
    public List<ProductsDTO> findMinPrice(Double price)  {
        priceNotNUll(price);
        List<Products> products = productsRespository.findByMinPrice(price);
        productExistList(products);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //procurar pelo maior preço
    public List<ProductsDTO> findMaxPrice(Double price)  {
        priceNotNUll(price);
        List<Products> products = productsRespository.findByMaxPrice(price);
        productExistList(products);
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }


    //funções para deixar o programa mais limpo e claro

    //verifica se o produto existe
    public static void productExistList(List<Products> products) {
        if(products.isEmpty()){
            throw new ProductNofFoundException("Produtos não existe");
        }
    }
    public static void productExist(Products products){
        if(products == null){
            throw  new ProductNofFoundException("Produtos não existe");
        }
    }
    //converte o produto para produto dto, fazendo assim o controller não ter acesso a entidade principal Produto
    private ProductsDTO convertToDto(Products products){
        return modelMapper.map(products, ProductsDTO.class);
    }

    //verifica se o preço é nulo ou menor que zero, se for ele lança uma exceção
    public static void priceNotNUll(Double price){
        if(price == null || price < 0){
            throw new PriceNotValidException("O preço do produto não pode ser null nem menor que zero");
        }
    }




}
