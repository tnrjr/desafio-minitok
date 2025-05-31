package com.tary.crud_produto.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer inStock;
    private String category;
    private String supplier;
}

