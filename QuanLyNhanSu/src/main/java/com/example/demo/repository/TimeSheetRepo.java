package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.Employee;
import com.example.demo.entity_model.TimeSheet;

public interface TimeSheetRepo extends JpaRepository<TimeSheet, Integer>{
	
	@Query("SELECT u FROM TimeSheet u JOIN u.employee d WHERE d.hoTen = :dName")
	Page<TimeSheet> search(@Param("dName") String dName, Pageable pageable);
	
//	@Query("SELECT u FROM (TimeSheet u JOIN u.employee d) JOIN  WHERE d.maNV = :dId")
//	Page<TimeSheet> search1(@Param("dId") int dId, Pageable pageable);
	
	@Query("SELECT u FROM TimeSheet u JOIN u.employee d WHERE d.department.maPhong = :dMaPhong")
	Page<TimeSheet> searchByDepartmentMaPhong(@Param("dMaPhong") int departmentMaPhong, Pageable pageable);
}
