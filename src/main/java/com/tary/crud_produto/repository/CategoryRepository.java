package com.tary.crud_produto.repository;

import com.tary.crud_produto.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
