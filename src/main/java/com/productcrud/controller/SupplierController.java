/**
 * 
 */
package com.productcrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.openapitools.api.SupplierApi;
import org.openapitools.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.productcrud.service.SupplierService;

@RestController
public class SupplierController implements SupplierApi{
	
	@Autowired
	private SupplierService supplierService;
	
	private final Logger logger = LoggerFactory.getLogger(SupplierController.class);

	@Override
	public ResponseEntity<Supplier> addSupplier(@Valid Supplier supplier) {
		try {
			return new ResponseEntity<>(this.supplierService.saveSupplier(supplier), HttpStatus.CREATED);
		} catch(Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteSupplier(Long supplierId) {
		try {	
			this.supplierService.deleteById(supplierId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<List<Supplier>> findAllSuppliers() {
		try {
			return new ResponseEntity<>(this.supplierService.getAllSuppliers(), HttpStatus.OK);
		} catch(Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Supplier> getSupplierById(Long supplierId) {
		try {
			return new ResponseEntity<>(this.supplierService.getSupplierById(supplierId), HttpStatus.OK);
		} catch(Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@Override
	public ResponseEntity<Supplier> updateSupplier(@Valid Supplier supplier) {
		try {
			Supplier existSupplier = this.supplierService.getSupplierById(supplier.getId());
			if (existSupplier.getId() != null) {
				return new ResponseEntity<>(this.supplierService.saveSupplier(supplier), HttpStatus.ACCEPTED);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch(Exception e) {
			this.logger.error(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}

	}

}
