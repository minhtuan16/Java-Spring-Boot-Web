package com.example.demo.entity_model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
}
