package com.tary.crud_produto.service;

import com.tary.crud_produto.dto.ProductRequestDTO;
import com.tary.crud_produto.dto.ProductResponseDTO;
import com.tary.crud_produto.exception.BadRequestException;
import com.tary.crud_produto.exception.ResourceNotFoundException;
import com.tary.crud_produto.model.Product;
import com.tary.crud_produto.repository.CategoryRepository;
import com.tary.crud_produto.repository.ProductRepository;
import com.tary.crud_produto.repository.SupplierRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public ProductService (ProductRepository repository){
        this.repository = repository;
    }

    public Product create(Product product){
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new BadRequestException("O nome é obrigatório.");
        }
        if (product.getPrice() == null) {
            throw new BadRequestException("O preço é obrigatório.");
        }
        if (product.getPrice() < 0) {
            throw new BadRequestException("O preço não pode ser negativo.");
        }
        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com ID: " + id));
    }


    public Product update(String id, Product product){
        Product existing = findById(id);

        if (product.getPrice() != null && product.getPrice() < 0) {
            throw new BadRequestException("O preço não pode ser negativo.");
        }

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
                .category(product.getCategory() != null ? product.getCategory().getName() : null)
                .supplier(product.getSupplier() != null ? product.getSupplier().getName() : null)
                .build();
    }


    public Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        if (dto.getCategoryId() != null) {
            product.setCategory(categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + dto.getCategoryId())));
        }

        if (dto.getSupplierId() != null) {
            product.setSupplier(supplierRepository.findById(dto.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com ID: " + dto.getSupplierId())));
        }

        return product;
    }


}
