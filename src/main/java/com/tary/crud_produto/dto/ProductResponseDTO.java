package com.tary.crud_produto.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Boolean inStock;
}
