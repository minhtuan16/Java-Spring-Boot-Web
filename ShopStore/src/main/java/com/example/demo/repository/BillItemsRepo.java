package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.BillItems;

public interface BillItemsRepo extends JpaRepository<BillItems, Integer>{
	
	@Query("SELECT bi FROM BillItems bi JOIN bi.bill b WHERE b.id = :bID")
	Page<BillItems> search_bID(@Param("bID") int x, Pageable pageable);
	
	@Query("SELECT bi FROM BillItems bi JOIN bi.product p WHERE p.id = :pID")
	Page<BillItems> search_pID(@Param("pID") int s, Pageable pageable);

}
