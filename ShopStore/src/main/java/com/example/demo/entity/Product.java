package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "{product.name.notempty}")
	@Column(unique = true)
	private String name;

	private double price;
	
	

	@NotEmpty(message = "{product.descriptionProduct.notempty}")
	private String descriptionProduct;
	
	private String imageURL;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

}
