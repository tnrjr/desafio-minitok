package com.tary.crud_produto.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryModelTest {

    @Test
    void testGettersAndSetters() {
        Category category = new Category();
        category.setId("cat-123");
        category.setName("Eletrônicos");

        assertEquals("cat-123", category.getId());
        assertEquals("Eletrônicos", category.getName());
    }
}
