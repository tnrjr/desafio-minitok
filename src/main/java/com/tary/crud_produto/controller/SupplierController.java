package com.tary.crud_produto.controller;

import com.tary.crud_produto.dto.SupplierRequestDTO;
import com.tary.crud_produto.dto.SupplierResponseDTO;
import com.tary.crud_produto.model.Supplier;
import com.tary.crud_produto.repository.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> create(@Valid @RequestBody SupplierRequestDTO request) {
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        Supplier saved = supplierRepository.save(supplier);
        return ResponseEntity.ok(toResponseDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> findAll() {
        List<SupplierResponseDTO> suppliers = supplierRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> findById(@PathVariable String id) {
        return supplierRepository.findById(id)
                .map(supplier -> ResponseEntity.ok(toResponseDTO(supplier)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        supplierRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private SupplierResponseDTO toResponseDTO(Supplier supplier) {
        return SupplierResponseDTO.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .build();
    }
}
