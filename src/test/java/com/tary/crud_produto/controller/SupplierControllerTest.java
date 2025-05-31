package com.tary.crud_produto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tary.crud_produto.dto.SupplierRequestDTO;
import com.tary.crud_produto.model.Supplier;
import com.tary.crud_produto.repository.SupplierRepository;
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

@WebMvcTest(SupplierController.class)
class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SupplierRepository supplierRepository;

    private Supplier supplier;

    @BeforeEach
    void setUp() {
        supplier = new Supplier();
        supplier.setId("sup-123");
        supplier.setName("Fornecedor ABC");
    }

    @Test
    void testCreateSupplier_Success() throws Exception {
        SupplierRequestDTO request = new SupplierRequestDTO();
        request.setName("Fornecedor ABC");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        mockMvc.perform(post("/api/suppliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("sup-123"))
                .andExpect(jsonPath("$.name").value("Fornecedor ABC"));
    }

    @Test
    void testFindAllSuppliers() throws Exception {
        when(supplierRepository.findAll()).thenReturn(List.of(supplier));

        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("sup-123"))
                .andExpect(jsonPath("$[0].name").value("Fornecedor ABC"));
    }

    @Test
    void testFindById_Success() throws Exception {
        when(supplierRepository.findById("sup-123")).thenReturn(Optional.of(supplier));

        mockMvc.perform(get("/api/suppliers/sup-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("sup-123"))
                .andExpect(jsonPath("$.name").value("Fornecedor ABC"));
    }

    @Test
    void testFindById_NotFound() throws Exception {
        when(supplierRepository.findById("sup-999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/suppliers/sup-999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteSupplier_Success() throws Exception {
        mockMvc.perform(delete("/api/suppliers/sup-123"))
                .andExpect(status().isNoContent());
    }
}
