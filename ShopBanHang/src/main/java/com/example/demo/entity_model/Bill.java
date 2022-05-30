package com.example.demo.entity_model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "bill")
@Data
public class Bill {
	
	@Id
	private int id;
	
	private Date buyDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
//	private String couponCode;
//	
//	private int discount;
}
