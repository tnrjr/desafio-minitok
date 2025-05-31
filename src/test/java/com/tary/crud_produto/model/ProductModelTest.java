package com.tary.crud_produto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductModelTest {

    @Test
    void testProductGettersAndSetters() {
        Category category = new Category();
        category.setId("cat-123");
        category.setName("Eletrônicos");

        Supplier supplier = new Supplier();
        supplier.setId("sup-123");
        supplier.setName("Fornecedor ABC");

        Product product = new Product();
        product.setId("prod-123");
        product.setName("Notebook");
        product.setDescription("Notebook Gamer");
        product.setPrice(5000.0);
        product.setInStock(5);
        product.setCategory(category);
        product.setSupplier(supplier);

        assertEquals("prod-123", product.getId());
        assertEquals("Notebook", product.getName());
        assertEquals("Notebook Gamer", product.getDescription());
        assertEquals(5000.0, product.getPrice());
        assertEquals(5, product.getInStock());
        assertEquals("Eletrônicos", product.getCategory().getName());
        assertEquals("Fornecedor ABC", product.getSupplier().getName());
    }
}
