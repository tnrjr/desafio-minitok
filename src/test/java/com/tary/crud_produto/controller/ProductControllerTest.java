package com.tary.crud_produto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tary.crud_produto.dto.ProductRequestDTO;
import com.tary.crud_produto.dto.ProductResponseDTO;
import com.tary.crud_produto.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        responseDTO = ProductResponseDTO.builder()
                .id("1")
                .name("Produto Teste")
                .description("Descrição")
                .price(100.0)
                .inStock(true)
                .build();
    }

    @Test
    void testCriarProduto() throws Exception {
        Mockito.when(service.create(any())).thenReturn(null); // Mocka o retorno se necessário
        Mockito.when(service.toResponseDTO(any())).thenReturn(responseDTO);

        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setName("Produto Teste");
        requestDTO.setPrice(100.0);
        requestDTO.setInStock(true);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Produto Teste"));
    }

    @Test
    void testBuscarTodos() throws Exception {
        Mockito.when(service.findAll()).thenReturn(List.of());
        Mockito.when(service.toResponseDTO(any())).thenReturn(responseDTO);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }
}
