package teste;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.produtos.Produtos.DTO.ProductsDTO;
import com.produtos.Produtos.controller.ProductController;
import com.produtos.Produtos.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductsByMinPrice_Success() throws Exception {
        // Mockando o comportamento do serviço
        List<ProductsDTO> mockProducts = new ArrayList<>();
        mockProducts.add(new ProductsDTO(/* parâmetros do construtor */));
        when(productsService.findByMinPriceOrEqual(1.0)).thenReturn(mockProducts);

        // Executando a requisição e verificando a resposta
        mockMvc.perform(get("/products/price/min/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))); // Verifica se há um produto retornado
    }

    @Test
    public void testGetProductsByMinPrice_NotFound() throws Exception {
        when(productsService.findByMinPriceOrEqual(1.0)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products/price/min/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Outros testes...
}