package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity_model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	
}
