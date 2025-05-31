package com.tary.crud_produto.service;

import com.tary.crud_produto.dto.ProductRequestDTO;
import com.tary.crud_produto.exception.ResourceNotFoundException;
import com.tary.crud_produto.model.Category;
import com.tary.crud_produto.model.Product;
import com.tary.crud_produto.model.Supplier;
import com.tary.crud_produto.repository.CategoryRepository;
import com.tary.crud_produto.repository.ProductRepository;
import com.tary.crud_produto.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private SupplierRepository supplierRepository;

    private ProductRequestDTO productRequestDTO;
    private Category category;
    private Supplier supplier;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository, categoryRepository, supplierRepository);

        category = new Category();
        category.setId("cat-123");
        category.setName("Eletrônicos");

        supplier = new Supplier();
        supplier.setId("sup-123");
        supplier.setName("Fornecedor ABC");

        productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setName("Produto Teste");
        productRequestDTO.setDescription("Descrição");
        productRequestDTO.setPrice(100.0);
        productRequestDTO.setInStock(10);
        productRequestDTO.setCategoryId("cat-123");
        productRequestDTO.setSupplierId("sup-123");
    }

    @Test
    void testToEntity_Success() {
        when(categoryRepository.findById("cat-123")).thenReturn(Optional.of(category));
        when(supplierRepository.findById("sup-123")).thenReturn(Optional.of(supplier));

        Product product = productService.toEntity(productRequestDTO);

        assertEquals("Produto Teste", product.getName());
        assertEquals("Descrição", product.getDescription());
        assertEquals(100.0, product.getPrice());
        assertEquals(10, product.getInStock());
        assertEquals("Eletrônicos", product.getCategory().getName());
        assertEquals("Fornecedor ABC", product.getSupplier().getName());
    }

    @Test
    void testToEntity_CategoryNotFound() {
        when(categoryRepository.findById("cat-123")).thenReturn(Optional.empty());
        when(supplierRepository.findById("sup-123")).thenReturn(Optional.of(supplier));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.toEntity(productRequestDTO));

        assertEquals("Categoria não encontrada com ID: cat-123", exception.getMessage());
    }

    @Test
    void testToEntity_SupplierNotFound() {
        when(categoryRepository.findById("cat-123")).thenReturn(Optional.of(category));
        when(supplierRepository.findById("sup-123")).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.toEntity(productRequestDTO));

        assertEquals("Fornecedor não encontrado com ID: sup-123", exception.getMessage());
    }
}
