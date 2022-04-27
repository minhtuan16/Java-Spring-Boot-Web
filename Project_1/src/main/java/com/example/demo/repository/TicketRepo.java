package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.Ticket;

public interface TicketRepo  extends JpaRepository<Ticket, Integer>{

	//select student where name = s
//	List<Student> findByName(String s);
//	
//	Student findByUsername(String s);
	
	//select student where name LIKE s
//	@Query("SELECT u FROM User u WHERE u.name LIKE :x") 
//	List<Ticket> search1(@Param("x") String s);
//	
//	@Query("SELECT u FROM User u WHERE u.role LIKE :x") 
//	List<Ticket> search2(@Param("x") String s);
//	
	
//	@Query("SELECT u FROM Ticket u WHERE u.id LIKE :x") 
//	List<Ticket> search(@Param("x") String s);
	
//	@Query("SELECT u FROM Student u WHERE u.id > 1") 
//	List<Student> search1();
}