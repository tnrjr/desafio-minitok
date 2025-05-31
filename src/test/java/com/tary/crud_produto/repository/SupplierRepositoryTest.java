package com.tary.crud_produto.repository;

import com.tary.crud_produto.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @AfterEach
    void tearDown() {
        supplierRepository.deleteAll();
    }

    @Test
    void testSaveAndFindSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("Fornecedor ABC");

        Supplier saved = supplierRepository.save(supplier);
        Supplier found = supplierRepository.findById(saved.getId()).orElseThrow();

        assertEquals("Fornecedor ABC", found.getName());
    }
}
