package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	@Query("SELECT c FROM Category c WHERE c.name LIKE :x")
	Page<Category> searchNameC(@Param("x") String x, Pageable pageable);
}
