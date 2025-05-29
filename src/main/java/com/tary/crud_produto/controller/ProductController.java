package com.tary.crud_produto.controller;

import com.tary.crud_produto.model.Product;
import com.tary.crud_produto.service.ProductService;
import jakarta.validation.Valid;
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
    public ProductResponseDTO create(@Valid @RequestBody ProductRequestDTO request) {
        return service.toResponseDTO(service.create(service.toEntity(request)));
    }

    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return service.findAll().stream().map(service::toResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO findById(@PathVariable String id) {
        return service.toResponseDTO(service.findById(id));
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable String id, @Valid @RequestBody ProductRequestDTO request) {
        return service.toResponseDTO(service.update(id, service.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        service.delete(id);
    }



}
