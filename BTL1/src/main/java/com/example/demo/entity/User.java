package com.example.demo.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "users")
@Data

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "{user.username.notempty}")
	@Column(unique = true)
	private String username;
	
	@NotEmpty(message = "{user.password.notempty}")
	private String password;
	 
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "role")
	@CollectionTable(name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"))
	private List<String> roles;
	
	@NotEmpty(message = "{user.mailUser.notempty}")
	private String mailUser;
}
