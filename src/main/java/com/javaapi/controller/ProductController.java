package com.javaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.model.Product;
import com.javaapi.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product createProduct(@RequestBody Product product) {
		return service.addProduct(product);
	}

	@GetMapping("/all")
	public List<Product> getproducts() {
		return service.findAllProducts();
	}
	
	@GetMapping
	public Product getProductByName(@RequestParam(required = true) String name) {
	    return service.getProductByName(name);
	}

	@GetMapping("/{productId}")
	public Product getProduct(@PathVariable String productId) {
		return service.getProductByProductId(productId);
	}

	@PutMapping("/{productId}")
	public Product modifyProduct(@PathVariable String productId, @RequestBody Product product) {
	    return service.updateProduct(productId, product); 
	}

	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable String productId) {
		return service.deleteProduct(productId);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleProductNotFoundException(RuntimeException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

}