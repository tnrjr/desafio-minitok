package com.tary.crud_produto.controller;

import com.tary.crud_produto.dto.CategoryRequestDTO;
import com.tary.crud_produto.dto.CategoryResponseDTO;
import com.tary.crud_produto.model.Category;
import com.tary.crud_produto.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO request) {
        Category category = new Category();
        category.setName(request.getName());
        Category saved = categoryRepository.save(category);
        return ResponseEntity.ok(CategoryResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build());
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> categories = categoryRepository.findAll().stream()
                .map(c -> CategoryResponseDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .build())
                .toList();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable String id) {
        return categoryRepository.findById(id)
                .map(c -> ResponseEntity.ok(CategoryResponseDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .build()))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
