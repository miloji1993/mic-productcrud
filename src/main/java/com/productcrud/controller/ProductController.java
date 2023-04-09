/**
 * 
 */
package com.productcrud.controller;


import java.util.List;

import javax.validation.Valid;

import org.openapitools.api.ProductApi;
import org.openapitools.model.Product;
import org.openapitools.model.ProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.productcrud.service.ProductService;

@RestController
public class ProductController implements ProductApi{
	
	@Autowired
	private ProductService productService;
	
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Override
	public ResponseEntity<Product> addProduct(@Valid ProductRequest productRequest) {
		try {
			return new ResponseEntity<>(this.productService.saveProduct(productRequest), HttpStatus.CREATED);
		} catch (Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteProduct(String eanCode) {
		try {
			this.productService.deleteByEan(eanCode);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<Product>> findAllProducts() {
		try {
			return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
		} catch (Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
		
		
	}

	@Override
	public ResponseEntity<Product> getProductByEan(String eanCode) {
		try {
			return new ResponseEntity<>(this.productService.getProductByEan(eanCode), HttpStatus.OK);
		} catch (Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Product> updateProduct(@Valid ProductRequest productRequest) {
		try {
			Product product = this.productService.getProductById(productRequest.getId());
			if (product.getId() != null) {
				return new ResponseEntity<>(this.productService.saveProduct(productRequest), HttpStatus.ACCEPTED);
			}else {
				return ResponseEntity.notFound().build();
			}
		} catch(Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}




}
