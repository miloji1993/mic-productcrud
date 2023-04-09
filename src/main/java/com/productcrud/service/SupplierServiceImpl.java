/**
 * 
 */
package com.productcrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.openapitools.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.productcrud.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	private ModelMapper modelMapper;

	SupplierServiceImpl() {
		this.modelMapper = new ModelMapper();
	}

	@Cacheable("suppliers")
	@Override
	public List<Supplier> getAllSuppliers() {
		return Optional.ofNullable(supplierRepository.findAll()).orElse(new ArrayList<>()).stream().map(s -> modelMapper.map(s, Supplier.class)).collect(Collectors.toList());
	}

	@Cacheable("suppliers")
	@Override
	public Supplier getSupplierById(Long id) {
		return modelMapper.map(supplierRepository.findById(id).orElse(new com.productcrud.model.Supplier()), Supplier.class);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		supplierRepository.deleteById(id);
	}

	@Transactional
	@Override
	public Supplier saveSupplier(Supplier supplier) {
		return modelMapper.map(supplierRepository.save(modelMapper.map(supplier, com.productcrud.model.Supplier.class)), Supplier.class);
	}
	
	

}
