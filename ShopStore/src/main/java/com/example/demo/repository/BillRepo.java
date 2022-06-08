package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{

	@Query("SELECT b FROM Bill b JOIN b.user u WHERE u.id = :x")
	Page<Bill> search_uID(@Param("x") int x, Pageable pageable);
	
	@Query("SELECT b FROM Bill b WHERE b.buyDate >= :fromDate")
	Page<Bill> searchFrom(@Param("fromDate") Date fromDate, Pageable pageable);
	
	@Query("SELECT b FROM Bill b WHERE b.buyDate <= :toDate")
	Page<Bill> searchTo(@Param("toDate") Date toDate, Pageable pageable);
	
	@Query("SELECT b FROM Bill b WHERE b.buyDate >= :fromDate AND b.buyDate <= :toDate")
	Page<Bill> searchFromTo(@Param("fromDate") Date fromDate, 
			@Param("toDate") Date toDate, Pageable pageable);
	
//	@Query("SELECT b FROM Bill b JOIN b.user u WHERE u.id = : uID "
//			+ "AND b.buyDate >= :fromDate AND b.buyDate <= :toDate")
//	Page<Bill> search_From_To_UserId(@Param("fromDate") Date fromDate, 
//			@Param("toDate") Date toDate,
//			@Param("uID") int userID, Pageable pageable);
	
	@Query("SELECT b FROM Bill b WHERE b.buyDate = :x")
	List<Bill> searchBill(@Param("x") Date x);
	
	
	@Query("SELECT count(b.id) as sl, MONTH(buyDate) as thang FROM Bill b GROUP BY MONTH(buyDate)")
	List<Object[]> thongKeTheoThang();
	 
	@Query("SELECT count(b.id) as sl, u.username AS Name FROM Bill b JOIN b.user u GROUP BY u.username")
	List<Object[]> thongKeTheoUsername();
	
	@Query("SELECT count(b.id) as sl, c.couponCode AS CouponCode FROM Bill b INNER JOIN Coupon c ON b.couponCode = c.couponCode GROUP BY c.couponCode")
	List<Object[]> thongKeTheoCouponCode();
}
