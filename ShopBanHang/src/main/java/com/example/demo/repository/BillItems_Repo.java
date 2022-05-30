package com.example.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.BillItems;
import com.example.demo.entity_model.Category;

public interface BillItems_Repo extends JpaRepository<BillItems, Integer>{

	@Query("SELECT bi FROM BillItems bi JOIN bi.bill b WHERE b.id = :bID")
	Page<BillItems> searchBillID(@Param("bID") int s1, Pageable pageable);

	@Query("SELECT bi FROM BillItems bi JOIN bi.product p WHERE p.id = :pID")
	Page<BillItems> searchProductID(@Param("pID") int s2, Pageable pageable);
	
//	@Query("SELECT bi FROM ((BillItems bi JOIN bi.bill b) JOIN bi.product p WHERE b.id = :bID AND p.id = :pID")
//	Page<BillItems> searchBillProductID(@Param("bID") int s1, @Param("pID") int s2, Pageable pageable);
}
