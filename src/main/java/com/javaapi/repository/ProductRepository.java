package com.javaapi.repository;

import com.javaapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
	 Product findByName(String name);
}
