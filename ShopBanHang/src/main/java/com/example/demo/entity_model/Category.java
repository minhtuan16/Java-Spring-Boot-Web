package com.example.demo.entity_model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "danhmuc")
@Data
public class Category {

	@Id
	private int id;
	
	@Column(name = "name", unique = true)
	private String name;
	
}
