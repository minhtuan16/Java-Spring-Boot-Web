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

import lombok.Data;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "SDT", unique = true)
	private int sDT;

	@Column(name = "Y_kien_khach_hang")
	private String yKienKH;
	
	@Column(name = "Thoi_gian_tiep_nhan")
	private Date ngayNhan;
	
	@Column(name = "Noi_dung_xu_ly")
	private String traLoiKH;

	@Column(name = "Trang_thai")
	private boolean trangThai;

	@Column(name = "Thoi_gian_xu_ly")
	private Date xuLy;
	
	@ManyToOne
	@JoinColumn(name = "id_phong_ban")
	private Department department;
}
