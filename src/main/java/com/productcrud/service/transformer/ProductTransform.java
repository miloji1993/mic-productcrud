/**
 * 
 */
package com.productcrud.service.transformer;

import java.util.List;
import java.util.Optional;

import org.openapitools.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.productcrud.model.Destination;
import com.productcrud.model.Product;
import com.productcrud.model.Supplier;
import com.productcrud.repository.SupplierRepository;

@Component
public class ProductTransform {

	@Autowired
	private SupplierRepository supplierRepository;

	public Product transformFromProductRequest(ProductRequest request) throws Exception {
		Product product = new Product();
		if (request != null) {
			product.setId(request.getId());
			product.setName(request.getName());
			Optional<Supplier> supplier = supplierRepository.findById(request.getSupplier().getId());
			if (supplier.isPresent()) {
				product.setSupplier(supplier.get());
			} else {
				throw new Exception("Supplier not exist");
			}
			Destination destination = List.of(Destination.values()).stream().filter(d -> d.getOrder() == request.getDestination()).findFirst().orElse(null);
			if (destination != null) {
				product.setDestination(destination.name());
			} else {
				throw new Exception("Destination not exist");
			}
			product.setEan(product.getSupplier().getId().toString() + request.getId().toString() + String.valueOf(destination.getOrder()));
		}
		
		return product;
	}

}
