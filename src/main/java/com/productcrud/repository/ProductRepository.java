/**
 * 
 */
package com.productcrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productcrud.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByEan(String ean);
	
	void deleteByEan(String ean);

}
