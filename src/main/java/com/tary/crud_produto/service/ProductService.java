package com.tary.crud_produto.service;

import com.tary.crud_produto.dto.ProductRequestDTO;
import com.tary.crud_produto.dto.ProductResponseDTO;
import com.tary.crud_produto.model.Product;
import com.tary.crud_produto.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService (ProductRepository repository){
        this.repository = repository;
    }

    public Product create(Product product){
        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("produto não encontrado"));
    }

    public Product update(String id, Product product){
        Product existing = findById(id);
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setInStock(product.getInStock());
        return repository.save(existing);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .inStock(product.getInStock())
                .build();
    }

    public Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }
}
