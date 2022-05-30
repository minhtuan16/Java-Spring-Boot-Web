package com.example.demo.entity_model;

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
@Table(name = "user")
@Data
public class User {

	@Id
	private int id;

	@NotEmpty(message = "{user.name.notempty}")
	private String name;

	@Column(unique = true)
	@NotEmpty(message = "{user.username.notempty}")
	private String username;

	@NotEmpty(message = "{user.password.notempty}")
	private String password;

//	@ElementCollection(fetch = FetchType.EAGER)
//	@Column(name = "role")
//	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//	private List<String> roles;
	
	@NotEmpty(message = "{user.roles.notempty}")
	private String roles;
	
	@NotEmpty(message = "{user.mailUser.notempty}")
	private String mailUser;
}
