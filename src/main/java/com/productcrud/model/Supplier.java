/**
 * 
 */
package com.productcrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "suppliers")
public class Supplier {
	
	@Id
	@Column(length = 7)
	private Long id;
	
	@NotBlank
	private String name;
	
	@OneToOne(mappedBy="supplier")
	private Product product;

	public Supplier(Long id, @NotBlank String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Supplier() {
		super();
	}
	
	

}
