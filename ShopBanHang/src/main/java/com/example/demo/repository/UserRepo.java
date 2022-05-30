package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

//	User findById(int x);
	
	@Query("SELECT u FROM User u WHERE u.username LIKE :x")
	Page<User> search1(@Param("x") String s, Pageable pageable);
}