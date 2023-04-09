/**
 * 
 */
package com.productcrud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.openapitools.model.ProductRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.productcrud.model.Product;
import com.productcrud.model.Supplier;
import com.productcrud.repository.ProductRepository;
import com.productcrud.service.transformer.ProductTransform;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

	@InjectMocks
	ProductServiceImpl service;
	
	@Mock
	ProductRepository repository;
	
	@Mock
	ProductTransform transform;

	@Test
	void getAllProductsTest() {
		
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product(45678L, "4596797456789", "product1", "colmenas", supplier);
		Product product2 = new Product(45679L, "4596797456792", "product2", "tiendaMercadonaEspaña", supplier);
		
		Mockito.when(this.repository.findAll()).thenReturn(List.of(product1, product2));
		
		List<org.openapitools.model.Product> products = service.getAllProducts();
		
		assertEquals(2, products.size());
		
	}
	
	@Test
	void getProductByEanTest() {
		
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product(45678L, "4596797456789", "product1", "colmenas", supplier);
		Mockito.when(this.repository.findByEan(any())).thenReturn(Optional.of(product1));
		
		org.openapitools.model.Product product = service.getProductByEan("4596797456789");
		
		assertEquals("product1", product.getName());
		
	}
	
	@Test
	void getProductByIdTest() {
		
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product(45678L, "4596797456789", "product1", "colmenas", supplier);
		Mockito.when(this.repository.findById(any())).thenReturn(Optional.of(product1));
		
		org.openapitools.model.Product product = service.getProductById(45678L);
		
		assertEquals("product1", product.getName());
		
	}
	
	@Test
	void deleteProductByEanTest() {
		
		Mockito.doNothing().when(this.repository).deleteByEan("4596797456789");
		
		this.service.deleteByEan("4596797456789");
		
		Mockito.verify(this.repository, Mockito.times(1)).deleteByEan("4596797456789");

	}
	
	@Test
	void saveProductTest() throws Exception {
		
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		ProductRequest productRequest = new ProductRequest();
		productRequest.setId(45678L);
		productRequest.setName("product1");
		productRequest.setDestination(9L);
		org.openapitools.model.Supplier supplierOrg = new org.openapitools.model.Supplier();
		supplierOrg.setId(4596797L);
		supplierOrg.setName("Hacendado");
		productRequest.setSupplier(supplierOrg);
		Product product1 = new Product(45678L, "4596797456789", "product1", "colmenas", supplier);
		
		Mockito.when(this.transform.transformFromProductRequest(productRequest)).thenReturn(product1);
		
		
		
		Mockito.when(this.repository.save(any())).thenReturn(product1);
		
		
		org.openapitools.model.Product product = this.service.saveProduct(productRequest);
		
		assertEquals("product1", product.getName());
		
	}

}
