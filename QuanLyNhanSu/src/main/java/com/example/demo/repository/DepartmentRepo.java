package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{
	
	@Query("SELECT u FROM Department u WHERE u.tenPhong LIKE :x")
	Page<Department> search(@Param("x") String s, Pageable pageable);
}
