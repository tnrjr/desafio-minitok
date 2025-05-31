package com.tary.crud_produto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupplierRequestDTO {
    @NotBlank(message = "O nome do fornecedor é obrigatório")
    private String name;
}
