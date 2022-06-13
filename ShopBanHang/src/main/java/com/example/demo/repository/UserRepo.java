package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.User;

public interface UserRepo  extends JpaRepository<User, Integer>{

//	User findById(int x);
	
	User findByUsername(String s);
	@Query("SELECT u FROM User u WHERE u.username LIKE :x")
	Page<User> search1(@Param("x") String s, Pageable pageable);
	
//	List<User> findByName(String s);
//
//	User findByUsername(String s);
//
//	@Query("SELECT u FROM User u WHERE u.name LIKE :x")
//	List<User> search(@Param("x") String s);
//
//	@Query("SELECT u FROM User u WHERE u.id > 1")
//	List<User> searchAI();
}