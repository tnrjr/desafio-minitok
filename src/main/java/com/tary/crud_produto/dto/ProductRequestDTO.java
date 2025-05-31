package com.tary.crud_produto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String description;

    @NotNull(message = "O preço é obrigatório")
    private Double price;

    @NotNull(message = "O estoque é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    private Integer inStock;

    @NotBlank(message = "O ID da categoria é obrigatório")
    private String categoryId;

    @NotBlank(message = "O ID do fornecedor é obrigatório")
    private String supplierId;
}
