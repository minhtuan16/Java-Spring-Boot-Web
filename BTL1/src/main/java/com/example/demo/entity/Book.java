package com.example.demo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class Book { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
 
	@Column(name="tieu_de", unique = true)
	@NotEmpty(message = "{book.title.notempty}") 
	private String title; 
	
	@Column(name="tac_gia")
	@NotEmpty(message = "{book.author.notempty}") 
	private String author; 
	
	@Column(name="mo_ta")
	private String desBook;
	
	private String bprice;
	
	@Column(name = "ngay_phat_hanh")
	/*
	 * @Temporal(TemporalType.TIME)
	 * 
	 * @DateTimeFormat(style = "HH:mm")
	 * 
	 * @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm")
	 */
	private Date releaseDay;
	
	@Column(name = "so_trang") 
	private Long pageNumber;
	
	private String imageURL;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name="product_category",joinColumns = {
//			@JoinColumn(name="product_id", referencedColumnName = "id")}
//	,inverseJoinColumns = {@JoinColumn(name="category_id",referencedColumnName = "id")})
//	private Set<Category> categories = new HashSet<Category>();
//	
}
