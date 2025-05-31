package com.tary.crud_produto.repository;

import com.tary.crud_produto.model.Product;
import com.tary.crud_produto.model.Category;
import com.tary.crud_produto.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        supplierRepository.deleteAll();
    }

    @Test
    void testSaveAndFindProduct() {
        Category category = new Category();
        category.setName("Eletrônicos");
        category = categoryRepository.save(category);

        Supplier supplier = new Supplier();
        supplier.setName("Fornecedor ABC");
        supplier = supplierRepository.save(supplier);

        Product product = new Product();
        product.setName("Smart TV");
        product.setPrice(2500.0);
        product.setCategory(category);
        product.setSupplier(supplier);

        Product saved = productRepository.save(product);
        Product found = productRepository.findById(saved.getId()).orElseThrow();

        assertEquals("Smart TV", found.getName());
        assertEquals("Eletrônicos", found.getCategory().getName());
        assertEquals("Fornecedor ABC", found.getSupplier().getName());
    }
}
