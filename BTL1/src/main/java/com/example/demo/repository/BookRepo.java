package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{

	@Query("SELECT k FROM Book k WHERE k.title LIKE :x")
	Page<Book> searchTitle(@Param("x") String x, Pageable pageable);
	
	@Query("SELECT k FROM Book k JOIN k.category c WHERE c.id = :cId")
	Page<Book> searchCategoryId(@Param("cId") int categoryId, Pageable pageable);
	
	@Query("SELECT k FROM Book k JOIN k.category c WHERE c.id = :cId AND k.title lIKE :s")
	Page<Book> searchCategoryIdAndTitle(@Param("cId") int categoryId,
			@Param("s") String s, Pageable pageable);

}
