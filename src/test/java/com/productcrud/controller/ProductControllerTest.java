/**
 * 
 */
package com.productcrud.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.openapitools.model.Product;
import org.openapitools.model.ProductRequest;
import org.openapitools.model.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.productcrud.service.ProductService;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {

	@InjectMocks
	private ProductController controller;
	
	@Mock
	private ProductService service;

	
	@Test
	void testAddProduct() throws Exception {
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product();
		product1.setId(45678L);
		product1.setEan("4596797456789");
		product1.setDestination("colmenas");
		product1.setName("product1");
		product1.setSupplier(supplier);
		
		Mockito.when(this.service.saveProduct(any())).thenReturn(product1);
		
		final ResponseEntity<Product> response = this.controller.addProduct(new ProductRequest());
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("product1", response.getBody().getName());
	}
	
	@Test
	void testAddProductThrowException() throws Exception {
		
		Mockito.when(this.service.saveProduct(any())).thenThrow(new NullPointerException("error"));
		
		final ResponseEntity<Product> response = this.controller.addProduct(new ProductRequest());
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}


	@Test
	void testDeleteProduct() {
		Mockito.doNothing().when(this.service).deleteByEan("");
		
		final ResponseEntity<?> response = this.controller.deleteProduct("");
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testDeleteProductThrowException() {
		Mockito.doThrow(new IllegalArgumentException("")).when(this.service).deleteByEan(null);
		
		final ResponseEntity<?> response = this.controller.deleteProduct(null);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	@Test
	void testFindAllProducts() {
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product();
		product1.setId(45678L);
		product1.setEan("4596797456789");
		product1.setDestination("colmenas");
		product1.setName("product1");
		product1.setSupplier(supplier);
		Mockito.when(this.service.getAllProducts()).thenReturn(List.of(product1));
		
		final ResponseEntity<List<Product>> response = this.controller.findAllProducts();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
	}
	
	@Test
	void testFindAllProductsThrowException() {
		
		Mockito.when(this.service.getAllProducts()).thenThrow(new NullPointerException("error"));
		
		final ResponseEntity<List<Product>> response = this.controller.findAllProducts();
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}


	@Test
	void testGetProductByEan() {
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product();
		product1.setId(45678L);
		product1.setEan("4596797456789");
		product1.setDestination("colmenas");
		product1.setName("product1");
		product1.setSupplier(supplier);
		Mockito.when(this.service.getProductByEan(any())).thenReturn(product1);
		
		final ResponseEntity<Product> response = this.controller.getProductByEan("4596797456789");
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("product1", response.getBody().getName());
	}
	
	@Test
	void testGetProductByEanThrowException() {
		
		Mockito.when(this.service.getProductByEan(any())).thenThrow(new NullPointerException("error"));
		
		final ResponseEntity<Product> response = this.controller.getProductByEan(null);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	
	@Test
	void testUpdateProduct() throws Exception {
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product();
		product1.setId(45678L);
		product1.setEan("4596797456789");
		product1.setDestination("colmenas");
		product1.setName("product1");
		product1.setSupplier(supplier);
		
		Mockito.when(this.service.getProductById(any())).thenReturn(product1);
		
		product1.setName("product2");
		
		Mockito.when(this.service.saveProduct(any())).thenReturn(product1);
		
		final ResponseEntity<Product> response = this.controller.updateProduct(new ProductRequest());
		
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("product2", response.getBody().getName());
	}
	
	@Test
	void testUpdateProductThrowNotFound() throws Exception {
		
		Mockito.when(this.service.getProductById(any())).thenReturn(new Product());
		
		final ResponseEntity<Product> response = this.controller.updateProduct(new ProductRequest());
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	void testUpdateProductThrowException() throws Exception {
		
		Mockito.when(this.service.getProductById(any())).thenThrow(new NullPointerException("erro"));
		
		final ResponseEntity<Product> response = this.controller.updateProduct(new ProductRequest());
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
	}

}
