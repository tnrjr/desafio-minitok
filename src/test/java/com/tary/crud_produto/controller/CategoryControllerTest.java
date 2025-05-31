package com.tary.crud_produto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tary.crud_produto.dto.CategoryRequestDTO;
import com.tary.crud_produto.model.Category;
import com.tary.crud_produto.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId("cat-123");
        category.setName("Eletrônicos");
    }

    @Test
    void testCreateCategory_Success() throws Exception {
        CategoryRequestDTO request = new CategoryRequestDTO();
        request.setName("Eletrônicos");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cat-123"))
                .andExpect(jsonPath("$.name").value("Eletrônicos"));
    }

    @Test
    void testFindAllCategories() throws Exception {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("cat-123"))
                .andExpect(jsonPath("$[0].name").value("Eletrônicos"));
    }

    @Test
    void testFindById_Success() throws Exception {
        when(categoryRepository.findById("cat-123")).thenReturn(Optional.of(category));

        mockMvc.perform(get("/api/categories/cat-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cat-123"))
                .andExpect(jsonPath("$.name").value("Eletrônicos"));
    }

    @Test
    void testFindById_NotFound() throws Exception {
        when(categoryRepository.findById("cat-999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/categories/cat-999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCategory_Success() throws Exception {
        mockMvc.perform(delete("/api/categories/cat-123"))
                .andExpect(status().isNoContent());
    }
}
