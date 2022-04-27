package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

	@Query("SELECT d FROM Department d WHERE d.name LIKE :x")
	List<Department> search(@Param("x") String s);
	
}
