package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date buyDate;
	
//	private String hoTen;
	
	private String phone;
	
	private String address;
	
	private String note;
	
//	private String couponCode;
//	private int discount;
	
	//private String totalPrice;
	
//	private double totalPayment;
	
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;
	
	private String buyer;
	
	
	
}
