package com.produtos.Produtos.controller;

import com.produtos.Produtos.exceptions.PriceNotValidException;
import com.produtos.Produtos.exceptions.ProductNotFoundException;
import com.produtos.Produtos.DTO.ProductsDTO;
import com.produtos.Produtos.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public List<ProductsDTO> getAllProducts() {
        return productsService.findAll();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<ProductsDTO>> getProductById(@PathVariable Long id) {
        try {
            Optional<ProductsDTO> product = productsService.findById(id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            System.err.println("Erro: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> updateProduct(@PathVariable Long id, @RequestParam ProductsDTO productsDTO){
        ProductsDTO udpdatedProduct = productsService.updateProducts(id, productsDTO);
        return ResponseEntity.ok(udpdatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProductsDTO> createProduct(@RequestBody ProductsDTO product) {
        ProductsDTO createdProduct = productsService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

    }
    @GetMapping("/{name}")
    public ResponseEntity<List<ProductsDTO>> getProductsByName(@PathVariable("name") String name) {
        // Decodifica o nome para garantir que espaços e caracteres especiais sejam tratados
        name = UriUtils.decode(name, StandardCharsets.UTF_8);

        List<ProductsDTO> products = productsService.findByName(name);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/price/min/{value}")
    public ResponseEntity<List<ProductsDTO>> getProductsByMinPrice(@PathVariable("value") Double value) {
        try{
            List<ProductsDTO> products = productsService.findByMinPriceOrEqual(value);
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
    @GetMapping("/price/max/{price}")
    public ResponseEntity<List<ProductsDTO>> getProductsByMaxPrice(@PathVariable("price") Double price){
        try{
            List<ProductsDTO> products = productsService.findByMaxPriceOrEqual(price);
            if(products.isEmpty()){
                throw  new ProductNotFoundException("Produto não encontrado");
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
