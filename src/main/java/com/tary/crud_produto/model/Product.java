package com.tary.crud_produto.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String  id;


    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String description;

    @NotNull(message = "O preço é obrigatório")
    @Positive
    private Double price;

    @Min(1)
    private Integer inStock;

    @DBRef
    private Category category;

    @DBRef
    private Supplier supplier;
}



