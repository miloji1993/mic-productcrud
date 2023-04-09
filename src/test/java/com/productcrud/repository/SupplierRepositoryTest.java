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

import com.productcrud.model.Supplier;

@DataJpaTest
class SupplierRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private SupplierRepository repository;

	@Test
	void findAllTest() {
		List<Supplier> suppliers = repository.findAll();
		
		assertEquals(2, suppliers.size());
	}
	
	@Test
	void findByIdTest() {
		
		Supplier supplier = repository.findById(8437008L).get();
		
		assertEquals("Hacendado", supplier.getName());
		
	}
	
	@Test
	void deleteByIdTest() {
		
		Supplier supplier = new Supplier(4567899L, "test");
		
		entityManager.persist(supplier);
		
		repository.deleteById(4567899L);
		
		List<Supplier> suppliers = repository.findAll();
		
		assertEquals(2, suppliers.size());
	}
	
	@Test
	void saveTest() {
		Supplier supplier = new Supplier(4567899L, "test");
		
		Supplier supplierResult = repository.save(supplier);
		
		assertEquals("test", supplierResult.getName());
		
	}

}
