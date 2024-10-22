package com.produtos.Produtos.testeConfig;

import com.produtos.Produtos.entities.Category;
import com.produtos.Produtos.entities.Products;
import com.produtos.Produtos.repository.ProductsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class testConfig implements CommandLineRunner {

    @Autowired
    private ProductsRespository productsRespository;

    @Override
    public void run(String... args) throws Exception {
        loadTestData();
    }

    private void loadTestData() {
        List<Products> products = Arrays.asList(
                new Products("Produto A", 10.0, "Produto para melhorar", new Category("Produtos A")),
                new Products("Produto B", 15.5, "produto para piorar", new Category("Produtos B")),
                new Products("Produto C", 8.99, "segue o baile", new Category("Produtos C")),
                new Products("Produto D", 25.0, "malhando o cerebroß", new Category("Produtos D"))
        );

        // Salva os produtos no banco de dados
        productsRespository.saveAll(products);
    }
}
