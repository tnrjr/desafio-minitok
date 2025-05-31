package com.tary.crud_produto.repository;

import com.tary.crud_produto.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void testSaveAndFindCategory() {
        Category category = new Category();
        category.setName("Eletrônicos");

        Category saved = categoryRepository.save(category);
        Category found = categoryRepository.findById(saved.getId()).orElseThrow();

        assertEquals("Eletrônicos", found.getName());
    }
}
