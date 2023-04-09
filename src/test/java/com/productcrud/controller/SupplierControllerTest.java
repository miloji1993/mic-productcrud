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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.productcrud.service.SupplierService;

@ExtendWith(SpringExtension.class)
class SupplierControllerTest {
	
	@InjectMocks
	private SupplierController controller;
	
	@Mock
	private SupplierService service;


	@Test
	void testAddSupplier() {
		
		org.openapitools.model.Supplier supplier = new org.openapitools.model.Supplier();
		supplier.setId(4567899L);
		supplier.setName("test");
		
		Mockito.when(this.service.saveSupplier(any())).thenReturn(supplier);
		
		final ResponseEntity<org.openapitools.model.Supplier> response = this.controller.addSupplier(supplier);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("test", response.getBody().getName());
		
	}
	
	@Test
	void testAddSupplierThrowException() {
		
		Mockito.when(this.service.saveSupplier(any())).thenThrow(new NullPointerException("error"));
		
		final ResponseEntity<org.openapitools.model.Supplier> response = this.controller.addSupplier(null);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
	}


	@Test
	void testDeleteSupplier() {
		Mockito.doNothing().when(this.service).deleteById(4567898L);
		
		final ResponseEntity<?> response = this.controller.deleteSupplier(4567898L);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testDeleteSupplierThrowException() {
		Mockito.doThrow(IllegalArgumentException.class).when(this.service).deleteById(null);
		
		final ResponseEntity<?> response = this.controller.deleteSupplier(null);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	
	@Test
	void testFindAllSuppliers() {
		org.openapitools.model.Supplier supplier = new org.openapitools.model.Supplier();
		supplier.setId(4567899L);
		supplier.setName("test");
		
		Mockito.when(this.service.getAllSuppliers()).thenReturn(List.of(supplier));
		
		final ResponseEntity<List<org.openapitools.model.Supplier>> response = this.controller.findAllSuppliers();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		
	}
	
	@Test
	void testFindAllSuppliersThrowException() {
	
		Mockito.when(this.service.getAllSuppliers()).thenThrow(new NullPointerException("error"));
		
		final ResponseEntity<List<org.openapitools.model.Supplier>> response = this.controller.findAllSuppliers();
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
	}

	@Test
	void testGetSupplierById() {
		org.openapitools.model.Supplier supplier = new org.openapitools.model.Supplier();
		supplier.setId(4567899L);
		supplier.setName("test");
		
		Mockito.when(this.service.getSupplierById(any())).thenReturn(supplier);
		
		final ResponseEntity<org.openapitools.model.Supplier> response = this.controller.getSupplierById(4567899L);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("test", response.getBody().getName());
	}
	
	@Test
	void testGetSupplierByIdThrowException() {
		Mockito.when(this.service.getSupplierById(any())).thenThrow(new NullPointerException("error"));
		
		final ResponseEntity<org.openapitools.model.Supplier> response = this.controller.getSupplierById(4567899L);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}

	
	@Test
	void testUpdateSupplier() {
		org.openapitools.model.Supplier supplier = new org.openapitools.model.Supplier();
		supplier.setId(4567899L);
		supplier.setName("test");
		
		Mockito.when(this.service.getSupplierById(any())).thenReturn(supplier);
		
		supplier.setName("test_modify");
		
		Mockito.when(this.service.saveSupplier(any())).thenReturn(supplier);
		
		final ResponseEntity<org.openapitools.model.Supplier> response = this.controller.updateSupplier(supplier);
		
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("test_modify", response.getBody().getName());
		
	}
	
	@Test
	void testUpdateSupplierThrowNotFound() {
		org.openapitools.model.Supplier supplier = new org.openapitools.model.Supplier();
		
		Mockito.when(this.service.getSupplierById(any())).thenReturn(supplier);
		
		final ResponseEntity<org.openapitools.model.Supplier> response = this.controller.updateSupplier(supplier);
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
	}
	
	@Test
	void testUpdateSupplierThrowException() {

		Mockito.when(this.service.getSupplierById(any())).thenThrow(new NullPointerException("error"));
		
		final ResponseEntity<org.openapitools.model.Supplier> response = this.controller.updateSupplier(null);
		
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		
	}


}
