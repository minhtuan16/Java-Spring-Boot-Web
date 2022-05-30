package com.example.demo.entity_model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "coupon")
@Data
public class Coupon {

	@Id
	private int id;
	
	@Column(unique = true)
	private String couponCode;
	
	private int discountAmount;
	
	private Date expiredDate;
}
