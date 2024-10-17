package com.produtos.Produtos.service;

import com.produtos.Produtos.entities.Products;
import com.produtos.Produtos.exceptions.ProductNotFoundException;
import com.produtos.Produtos.productsDTO.ProductsDTO;
import com.produtos.Produtos.repository.ProductsRespository;
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

    private final ProductsUtil productsUtil = new ProductsUtil();

    private final ModelMapper modelMapper = new ModelMapper();


    //utilizando stream api para deixar o programa mais limpo e facil de entender
    public List<ProductsDTO> findAll(){
        return productsRespository.findAll().stream().map(productsUtil::convertToDto).collect(Collectors.toList());
    }

    //buscar por id
    public Optional<ProductsDTO> findById(Long id) {

        return Optional.ofNullable(productsRespository.findById(id).map(productsUtil::convertToDto).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado" + id)));
    }
    //adicionar novo produto
    public ProductsDTO addProduct(ProductsDTO productsDTO){
        Products products = productsUtil.convertProducts(productsDTO);
        productsRespository.save(products);
        return productsUtil.convertToDto(products);

    }

    //atualizar produtos
    public ProductsDTO updateProducts(Long id, ProductsDTO productsDTO){
        Products products = productsRespository.findById(id).orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        modelMapper.map(productsDTO, products);
        productsRespository.save(products);
        return productsUtil.convertToDto(products);
    }
    public void deleteProduct(Long id){
        if(!productsRespository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado");
        }
        productsRespository.deleteById(id);
    }

    public List<ProductsDTO>findByName(String name){
        return productsRespository.findByProductName(name).stream().map(productsUtil::convertToDto).collect(Collectors.toList());
    }


    //procurar por menor preço
    public List<ProductsDTO> findByMinPrice(Double price)  {
        productsUtil.validatePrice(price);
        List<Products> products = productsRespository.findByPriceProductLessThanEqual(price);
        productsUtil.productExistList(products);
        return products.stream().map(productsUtil::convertToDto).collect(Collectors.toList());
    }

    //procurar pelo maior preço
    public List<ProductsDTO> findByMaxPrice(Double price)  {
        productsUtil.validatePrice(price);
        List<Products> products = productsRespository.findByPriceProductGreaterThanEqual(price);
        productsUtil.productExistList(products);
        return products.stream().map(productsUtil::convertToDto).collect(Collectors.toList());
    }





}
