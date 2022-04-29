package com.example.demo.entity_model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "department")
@Data
public class Department {

	@Id
	private int id;
	
	@Column(name = "name_phong_ban", unique = true)
	private String name;
	
//	@Column(name = "ngay_tao")
	private Date ngayTao;
	
	
	//tro nguoc lai thang department trong Ticket.java
	// Fetch - LAZY: se lay thang department truoc 
	//					, khi nao goi thang ticket moi lay thang ticket len
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY) 
	private List<Ticket> tickets;
}
