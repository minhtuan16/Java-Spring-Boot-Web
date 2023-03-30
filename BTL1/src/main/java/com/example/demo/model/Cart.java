package com.example.demo.model;

import lombok.Data;

@Data
public class Cart {

	private int cid;
	private String cname;
	private String cprice;
	private int cquantity;
	
	private String image;

	public Cart(int cid, String cname, String cprice, int cquantity, String image) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cprice = cprice;
		this.cquantity = cquantity;
		this.image = image;
	}

	
	
	
}
