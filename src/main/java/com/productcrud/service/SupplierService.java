/**
 * 
 */
package com.productcrud.service;

import java.util.List;

import org.openapitools.model.Supplier;

public interface SupplierService {
	
	List<Supplier> getAllSuppliers();
	
	Supplier getSupplierById(Long id);
	
	void deleteById(Long id);
	
	Supplier saveSupplier(Supplier supplier);

}
