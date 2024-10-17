package com.produtos.Produtos.service.utils;

import com.produtos.Produtos.entities.Products;
import com.produtos.Produtos.exceptions.PriceNotValidException;
import com.produtos.Produtos.exceptions.ProductNotFoundException;
import com.produtos.Produtos.DTO.ProductsDTO;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductsUtil {



    //verifica se o produto existe
    public static void productExistList(List<Products> products) {
        if(products.isEmpty()){
            throw new ProductNotFoundException("Produtos não existe");
        }
    }
    public static void productExist(Products products){
        if(products == null){
            throw  new ProductNotFoundException("Produtos não existe");
        }
    }

    //verifica se o preço é nulo ou menor que zero, se for ele lança uma exceção
    public static void validatePrice(Double price){
        if(price == null || price < 0){
            throw new PriceNotValidException("O preço do produto não pode ser null nem menor que zero");
        }
    }


}
