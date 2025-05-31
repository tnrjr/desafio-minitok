package com.tary.crud_produto.repository;

import com.tary.crud_produto.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
}
