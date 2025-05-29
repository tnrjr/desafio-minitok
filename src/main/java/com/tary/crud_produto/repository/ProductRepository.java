package com.tary.crud_produto.repository;

import com.tary.crud_produto.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String > {
}
