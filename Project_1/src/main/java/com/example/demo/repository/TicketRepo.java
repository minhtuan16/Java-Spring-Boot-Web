package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.Ticket;

public interface TicketRepo  extends JpaRepository<Ticket, Integer>{

//	List<Ticket> findByID(int x);
//	
//	List<Ticket> findBySDT(String phone);
//	
//	//select student where name LIKE s
//	@Query("SELECT u FROM Ticket u WHERE u.sDT LIKE :x") 
//	List<Ticket> searchSDT(@Param("x") String s);
//	
//	@Query("SELECT u FROM Ticket u WHERE u.ngayNhan >= :from") 
//	List<Ticket> searchByFrom(@Param("from") Date from);
//
//	@Query("SELECT u FROM Ticket u WHERE u.ngayNhan <= :to") 
//	List<Ticket> searchByTo(@Param("to") Date to);
//	
//	@Query("SELECT u FROM Ticket u WHERE u.ngayNhan >= :from AND u.ngayNhan <= :to") 
//	List<Ticket> searchByFromTo(@Param("from") Date from, @Param("to") Date to);
//	
//	@Query("SELECT u FROM Ticket u JOIN u.department d WHERE d.id = :dID") 
//	List<Ticket> searchByDepartmentId(@Param("dID") int dID);
//	
//	@Query("SELECT u FROM Ticket u JOIN u.department d WHERE d.name = :dName") 
//	List<Ticket> searchByDepartmentName(@Param("dName") String dName);
	
}