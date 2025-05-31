package com.tary.crud_produto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String name;
}
