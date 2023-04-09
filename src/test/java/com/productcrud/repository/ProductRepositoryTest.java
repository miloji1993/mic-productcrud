/**
 * 
 */
package com.productcrud.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.productcrud.model.Product;
import com.productcrud.model.Supplier;

@DataJpaTest
class ProductRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ProductRepository repository;
	
	@Test
	void findAllTest() {
		
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product(45678L, "4596797456789", "product1", "colmenas", supplier);
		Product product2 = new Product(45679L, "4596797456792", "product2", "tiendaMercadonaEspaña", supplier);
		
		entityManager.persist(product1);
		entityManager.persist(product2);
		
		List<Product> products = repository.findAll();
		
		assertEquals(3, products.size());
		
	}
	
	@Test
	void findByEanTest() {
		
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product(45678L, "4596797456789", "product1", "colmenas", supplier);
		
		entityManager.persist(product1);
		
		Product product = repository.findByEan("4596797456789").get();
		
		assertEquals("product1", product.getName());
		
	}
	
	@Test
	void deleteByEanTest() {
		
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product1 = new Product(45678L, "4596797456789", "product1", "colmenas", supplier);
		
		entityManager.persist(product1);
		
		repository.deleteByEan("4596797456789");
		
		List<Product> products = repository.findAll();
		
		assertEquals(1, products.size());
	}

	@Test
	void savetest() {
		Supplier supplier = new Supplier();
		supplier.setId(4596797L);
		supplier.setName("Hacendado");
		
		Product product = new Product(45678L, "4596797456789", "product", "colmenas", supplier);
		
		Product newProduct = this.repository.save(product);
		
		assertEquals("product", newProduct.getName());
		assertEquals("colmenas", newProduct.getDestination());
	}
	

}
