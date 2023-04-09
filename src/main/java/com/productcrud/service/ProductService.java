/**
 * 
 */
package com.productcrud.service;

import java.util.List;

import org.openapitools.model.Product;
import org.openapitools.model.ProductRequest;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	Product getProductByEan(String ean);
	
	Product getProductById(Long id);
	
	void deleteByEan(String ean);
	
	Product saveProduct(ProductRequest product) throws Exception;

}
