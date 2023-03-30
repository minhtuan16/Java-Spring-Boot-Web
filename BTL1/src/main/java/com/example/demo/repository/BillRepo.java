package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{

	@Query("SELECT b FROM Bill b WHERE b.buyDate >= :fromDate")
	Page<Bill> searchFrom(@Param("fromDate") Date fromDate, Pageable pageable);
	
	@Query("SELECT b FROM Bill b WHERE b.buyDate <= :toDate")
	Page<Bill> searchTo(@Param("toDate") Date toDate, Pageable pageable);
	
	@Query("SELECT b FROM Bill b WHERE b.buyDate >= :fromDate AND b.buyDate <= :toDate")
	Page<Bill> searchFromTo(@Param("fromDate") Date fromDate, 
			@Param("toDate") Date toDate, Pageable pageable);
}
