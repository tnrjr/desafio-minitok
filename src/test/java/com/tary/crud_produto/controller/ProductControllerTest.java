package com.tary.crud_produto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tary.crud_produto.dto.ProductRequestDTO;
import com.tary.crud_produto.dto.ProductResponseDTO;
import com.tary.crud_produto.model.Category;
import com.tary.crud_produto.model.Product;
import com.tary.crud_produto.model.Supplier;
import com.tary.crud_produto.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private Category category;
    private Supplier supplier;

    @BeforeEach
    void setUp() {
        // Declarar e inicializar category e supplier
        category = new Category();
        category.setId("cat-123");
        category.setName("Eletrônicos");

        supplier = new Supplier();
        supplier.setId("sup-123");
        supplier.setName("Fornecedor ABC");
    }

    @Test
    void testCreateProduct_Success() throws Exception {
        Product product = new Product();
        product.setId("prod-123");
        product.setName("Produto Teste");
        product.setDescription("Descrição");
        product.setPrice(100.0);
        product.setInStock(5);
        product.setCategory(category);
        product.setSupplier(supplier);

        ProductRequestDTO request = new ProductRequestDTO();
        request.setName("Produto Teste");
        request.setDescription("Descrição");
        request.setPrice(100.0);
        request.setInStock(5);
        request.setCategoryId("cat-123");
        request.setSupplierId("sup-123");

        when(productService.toEntity(any(ProductRequestDTO.class))).thenReturn(product);
        when(productService.create(any(Product.class))).thenReturn(product);
        when(productService.toResponseDTO(any(Product.class))).thenReturn(
                ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .inStock(product.getInStock())
                        .category(product.getCategory().getName())
                        .supplier(product.getSupplier().getName())
                        .build()
        );


        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Produto Teste"))
                .andExpect(jsonPath("$.category").value("Eletrônicos"))
                .andExpect(jsonPath("$.supplier").value("Fornecedor ABC"));
    }
}
