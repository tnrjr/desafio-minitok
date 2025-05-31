package com.tary.crud_produto.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Recurso não encontrado");
        assertEquals("Recurso não encontrado", ex.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionForCategory() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Categoria não encontrada");
        assertEquals("Categoria não encontrada", ex.getMessage());
    }

    @Test
    void testResourceNotFoundExceptionForSupplier() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Fornecedor não encontrado");
        assertEquals("Fornecedor não encontrado", ex.getMessage());
    }

}
