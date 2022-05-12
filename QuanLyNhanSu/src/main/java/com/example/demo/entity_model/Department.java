package com.example.demo.entity_model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "department")
@Data
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maPhong;
	
	@NotEmpty(message = "{department.tenPhong.notempty}")
	private String tenPhong;
	
	@NotEmpty(message = "{department.tenPhong.notempty}")
	private String moTa;
	
//	@NotEmpty(message = "{department.heSoCV.notempty}")
	private float heSoCV;
}
