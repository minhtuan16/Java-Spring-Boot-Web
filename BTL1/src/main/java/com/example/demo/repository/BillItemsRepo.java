package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.BillItems;
import com.example.demo.entity.Category;

public interface BillItemsRepo extends JpaRepository<BillItems, Integer>{

	@Query("SELECT bi FROM BillItems bi JOIN bi.book b WHERE b.id = :bID")
	Page<BillItems> searchProductID(@Param("bID") int s2, Pageable pageable);
}
