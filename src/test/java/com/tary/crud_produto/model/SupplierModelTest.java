package com.tary.crud_produto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierModelTest {

    @Test
    void testGettersAndSetters() {
        Supplier supplier = new Supplier();
        supplier.setId("sup-123");
        supplier.setName("Fornecedor ABC");

        assertEquals("sup-123", supplier.getId());
        assertEquals("Fornecedor ABC", supplier.getName());
    }
}
