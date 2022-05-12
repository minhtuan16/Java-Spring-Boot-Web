package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
//	@Query("SELECT u FROM Employee u WHERE u.hoTen LIKE :x")
//	Page<Employee> search1(@Param("x") String s, Pageable pageable);
	
	@Query("SELECT u FROM Employee u JOIN u.department d WHERE d.maPhong = :dMaPhong AND u.hoTen LIKE :uHoTen")
	Page<Employee> searchByDepartmentMaPhong(@Param("dMaPhong") int departmentMaPhong, 
			@Param("uHoTen") String hoTen,
			Pageable pageable);
}
