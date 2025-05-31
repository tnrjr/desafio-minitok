package com.tary.crud_produto.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ProductRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidationFailsForMissingFields() {
        ProductRequestDTO dto = new ProductRequestDTO();
        var violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testValidationFailsForNegativeInStock() {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setName("Produto Teste");
        dto.setPrice(100.0);
        dto.setInStock(-5);
        dto.setCategoryId("cat-123");
        dto.setSupplierId("sup-123");

        var violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
