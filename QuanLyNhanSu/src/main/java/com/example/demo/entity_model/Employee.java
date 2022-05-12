package com.example.demo.entity_model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maNV;
	
	@NotEmpty(message = "{employee.hoTen.notempty}")
	private String hoTen;
	
	@NotEmpty(message = "{employee.diaChi.notempty}")
	private String diaChi;
	
	@NotEmpty(message = "{employee.soDienThoai.notempty}")
	private String soDienThoai;

	@NotEmpty(message = "{employee.bacLuong.notempty}")
	private int bacLuong;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
}
