package com.tary.crud_produto.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SupplierRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidationFailsForMissingName() {
        SupplierRequestDTO dto = new SupplierRequestDTO();
        var violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
    }
}
