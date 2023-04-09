package com.productcrud.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@Column(length = 5)
	private Long id;
	
	@NotBlank
	@Size(min = 13, max = 13)
	private String ean;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String destination;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="supplier_id", referencedColumnName = "id")
	private Supplier supplier;

	public Product(Long id, @NotBlank @Size(min = 13, max = 13) String ean, @NotBlank String name,
			@NotBlank String destination, Supplier supplier) {
		super();
		this.id = id;
		this.ean = ean;
		this.name = name;
		this.destination = destination;
		this.supplier = supplier;
	}

	public Product() {
		super();
	}
	
	
	
}
