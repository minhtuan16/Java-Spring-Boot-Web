package com.example.demo.entity_model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bill_items")
@Data
public class BillItems {

	@Id
	private int id;
	
	private int quantity;
	
	private double buyPrice;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;
}
