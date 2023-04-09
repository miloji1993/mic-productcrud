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
import org.openapitools.model.Product;
import org.openapitools.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.productcrud.repository.ProductRepository;
import com.productcrud.service.transformer.ProductTransform;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductTransform productTransform;
	
	private ModelMapper modelMapper;

	ProductServiceImpl() {
		this.modelMapper = new ModelMapper();
	}

	@Cacheable("products")
	@Override
	public List<Product> getAllProducts() {
		return Optional.ofNullable(productRepository.findAll()).orElse(new ArrayList<>()).stream().map(p -> modelMapper.map(p, Product.class)).collect(Collectors.toList());
	}

	@Cacheable("products")
	@Override
	public Product getProductByEan(String ean) {
		return modelMapper.map(productRepository.findByEan(ean).orElse(new com.productcrud.model.Product()), Product.class);
	}

	@Transactional
	@Override
	public void deleteByEan(String ean) {
		this.productRepository.deleteByEan(ean);
	}

	@Transactional
	@Override
	public Product saveProduct(ProductRequest product) throws Exception {
		return modelMapper.map(this.productRepository.save(this.productTransform.transformFromProductRequest(product)), Product.class);
	}

	@Override
	public Product getProductById(Long id) {
		return modelMapper.map(productRepository.findById(id).orElse(new com.productcrud.model.Product()), Product.class);
	}

}
