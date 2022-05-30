package com.example.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.Category;
import com.example.demo.entity_model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.name LIKE :x")
	Page<Product> search1(@Param("x") String s, Pageable pageable);
	
	@Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :cID")
	Page<Product> searchByCategoryId(@Param("cID") int categoryID, Pageable pageable);
	
	@Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :cID AND p.name LIKE :x")
	Page<Product> search12(@Param("cID") int categoryID,
			@Param("x") String s1, Pageable pageable);
}
