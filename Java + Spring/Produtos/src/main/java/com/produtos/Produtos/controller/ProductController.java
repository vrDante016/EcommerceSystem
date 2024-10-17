package com.produtos.Produtos.controller;

import com.produtos.Produtos.exceptions.PriceNotValidException;
import com.produtos.Produtos.exceptions.ProductNotFoundException;
import com.produtos.Produtos.productsDTO.ProductsDTO;
import com.produtos.Produtos.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public List<ProductsDTO> getAllProducts() {
        return productsService.findAll();
    }

    @PostMapping
    public ProductsDTO createProduct(@RequestBody ProductsDTO product) {
        return productsService.addProduct(product);
    }
    @GetMapping("/{name}")
    public ResponseEntity<List<ProductsDTO>> getProductsByName(@PathVariable("name") String name) {
        // Decodifica o nome para garantir que espaços e caracteres especiais sejam tratados
        name = UriUtils.decode(name, StandardCharsets.UTF_8);

        List<ProductsDTO> products = productsService.findByName(name);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/price/{price}")
    public ResponseEntity<List<ProductsDTO>> getProductsByMinPrice(@PathVariable("price") Double price) {
        try{
            List<ProductsDTO> products = productsService.findByMinPrice(price);
            if(products.isEmpty()){
                throw new ProductNotFoundException("produto não encontrado");
            }
            return ResponseEntity.ok(products);
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (PriceNotValidException e){
            System.err.println("Preço invalido");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
