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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.productcrud.model.Supplier;
import com.productcrud.repository.SupplierRepository;

@ExtendWith(SpringExtension.class)
class SupplierServiceTest {
	
	@InjectMocks
	private SupplierServiceImpl service;
	
	@Mock
	private SupplierRepository repository;

	@Test
	void getAllSuppliersTest() {
		Supplier supplier = new Supplier(4567899L, "test");
		Supplier supplier2 = new Supplier(4567890L, "test2");
		
		Mockito.when(this.repository.findAll()).thenReturn(List.of(supplier, supplier2));
		
		List<org.openapitools.model.Supplier> suppliers = this.service.getAllSuppliers();
		
		assertEquals(2, suppliers.size());
	}

	@Test
	void getSupplierByIdTest() {
		Supplier supplier = new Supplier(4567899L, "test");
		Mockito.when(this.repository.findById(4567899L)).thenReturn(Optional.of(supplier));
		
		org.openapitools.model.Supplier result = this.service.getSupplierById(4567899L);
		
		assertEquals("test", result.getName());
		
	}

	@Test
	void deleteByIdTest() {
		Mockito.doNothing().when(this.repository).deleteById(4567899L);
		
		this.service.deleteById(4567899L);
		
		Mockito.verify(this.repository, Mockito.times(1)).deleteById(4567899L);
	}

	@Test
	void saveSupplierTest() {
		
		Supplier supplier = new Supplier(4567899L, "test");
		Mockito.when(this.repository.save(any())).thenReturn(supplier);
		
		org.openapitools.model.Supplier result = this.service.saveSupplier(new org.openapitools.model.Supplier());
		
		assertEquals("test", result.getName());
		
	}

}
