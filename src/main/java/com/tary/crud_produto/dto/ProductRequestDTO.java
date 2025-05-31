package com.tary.crud_produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String description;

    @NotNull(message = "O preço é obrigatório")
    private Double price;

    private Integer inStock;

    private String categoryId;

    private String supplierId;
}

