package com.example.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.name LIKE :s")
	Page<Product> searchNameP(@Param("s") String s, Pageable pageable);
	
	@Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :cId")
	Page<Product> searchCategoryId(@Param("cId") int categoryId, Pageable pageable);
	
	@Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :cId AND p.name lIKE :s")
	Page<Product> searchCategoryIdAndNameP(@Param("cId") int categoryId,
			@Param("s") String s, Pageable pageable);
}
