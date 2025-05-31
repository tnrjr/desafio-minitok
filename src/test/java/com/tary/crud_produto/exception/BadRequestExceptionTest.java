package com.tary.crud_produto.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadRequestExceptionTest {

    @Test
    void testExceptionMessage() {
        BadRequestException ex = new BadRequestException("Erro na requisição");
        assertEquals("Erro na requisição", ex.getMessage());
    }
}
