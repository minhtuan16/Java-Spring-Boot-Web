package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer>{

	Coupon findByCouponCode(String s);
	
	@Query("SELECT c FROM Coupon c WHERE c.couponCode LIKE :x")
	Page<Coupon> searchCC(@Param("x") String x, Pageable pageable);
}
