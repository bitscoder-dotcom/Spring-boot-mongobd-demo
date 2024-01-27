package com.bitscoder.mongodemo.repository;

import com.bitscoder.mongodemo.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
