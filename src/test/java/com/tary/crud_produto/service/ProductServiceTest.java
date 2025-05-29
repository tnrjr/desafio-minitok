package com.tary.crud_produto.service;

import com.tary.crud_produto.model.Product;
import com.tary.crud_produto.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private Product produtoExemplo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        produtoExemplo = Product.builder()
                .id("1")
                .name("Produto Teste")
                .description("Descrição")
                .price(100.0)
                .inStock(true)
                .build();
    }

    @Test
    void testCriarProduto() {
        when(repository.save(any())).thenReturn(produtoExemplo);
        Product criado = service.create(produtoExemplo);
        assertNotNull(criado);
        assertEquals("Produto Teste", criado.getName());
    }

    @Test
    void testBuscarTodos() {
        when(repository.findAll()).thenReturn(List.of(produtoExemplo));
        List<Product> produtos = service.findAll();
        assertFalse(produtos.isEmpty());
    }

    @Test
    void testBuscarPorId() {
        when(repository.findById("1")).thenReturn(Optional.of(produtoExemplo));
        Product encontrado = service.findById("1");
        assertEquals("Produto Teste", encontrado.getName());
    }

    @Test
    void testAtualizarProduto() {
        when(repository.findById("1")).thenReturn(Optional.of(produtoExemplo));
        when(repository.save(any())).thenReturn(produtoExemplo);

        Product atualizado = service.update("1", produtoExemplo);
        assertEquals("Produto Teste", atualizado.getName());
    }

    @Test
    void testDeletarProduto() {
        doNothing().when(repository).deleteById("1");
        service.delete("1");
        verify(repository, times(1)).deleteById("1");
    }
}