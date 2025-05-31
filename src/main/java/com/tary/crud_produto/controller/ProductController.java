package com.tary.crud_produto.controller;

import com.tary.crud_produto.model.Product;
import com.tary.crud_produto.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tary.crud_produto.dto.ProductRequestDTO;
import com.tary.crud_produto.dto.ProductResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO request) {
        ProductResponseDTO newProduct = service.toResponseDTO(service.create(service.toEntity(request)));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> productList = service.findAll().stream().map(service::toResponseDTO).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable String id) {
        ProductResponseDTO product = service.toResponseDTO(service.findById(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable String id, @Valid @RequestBody ProductRequestDTO request) {
        ProductResponseDTO updateProduct = service.toResponseDTO(service.update(id, service.toEntity(request)));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
