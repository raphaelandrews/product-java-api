package com.javaapi.service;

import com.javaapi.model.Product;
import com.javaapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product addProduct(Product product) {
		return repository.save(product);
	}

	public List<Product> findAllProducts() {
		return repository.findAll();
	}

	public Product getProductByProductId(String productId) {
		return repository.findById(productId).get();
	}

	public Product getProductByName(String name) {
	    Product product = repository.findByName(name);
	    
	    if (product != null) {
	        return product;
	    } else {
	        throw new RuntimeException("Product not found with name: " + name);
	    }
	}

	public Product updateProduct(String productId, Product productRequest) {
		Product existingProduct = repository.findById(productId).orElse(null);

		if (existingProduct != null) {
			existingProduct.setDescription(productRequest.getDescription());
			existingProduct.setName(productRequest.getName());
			existingProduct.setPrice(productRequest.getPrice());
			existingProduct.setImageUrl(productRequest.getImageUrl());
			existingProduct.setCategory(productRequest.getCategory());
			return repository.save(existingProduct);
		} else {
			return null;
		}
	}

	public String deleteProduct(String productId) {
		repository.deleteById(productId);
		return productId + " product deleted from catalog ";
	}
}