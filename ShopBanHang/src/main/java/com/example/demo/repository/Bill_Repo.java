package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity_model.Bill;

public interface Bill_Repo extends JpaRepository<Bill, Integer> {

	@Query("SELECT b FROM Bill b JOIN b.user u WHERE u.id = :uID AND b.buyDate >= :from AND b.buyDate <= :to")
	Page<Bill> search_User_fromDate_toDate(@Param("uID") int s, @Param("from") Date from, @Param("to") Date to,
			Pageable pageable);

	@Query("SELECT b FROM Bill b WHERE b.buyDate >= :from AND b.buyDate <= :to")
	Page<Bill> search_fromDate_toDate(@Param("from") Date from, @Param("to") Date to, Pageable pageable);

	@Query("SELECT b FROM Bill b WHERE b.buyDate >= :from")
	Page<Bill> searchByFrom(@Param("from") Date from, Pageable pageable);

	@Query("SELECT b FROM Bill b WHERE b.buyDate <= :to")
	Page<Bill> searchByTo(@Param("to") Date to, Pageable pageable);

	@Query("SELECT b FROM Bill b JOIN b.user u WHERE u.id = :uID")
	Page<Bill> searchUser(@Param("uID") int userID, Pageable pageable);

	@Query("SELECT b FROM Bill b WHERE b.buyDate >= :date")
	List<Bill> searchBill(@Param("date") Date date);

	Bill findByCouponCode(String s);

	// thong ke theo thang
	@Query("SELECT count(b.id) as SL , MONTH(buyDate) as Thang FROM Bill b GROUP BY MONTH(buyDate)")
	List<Object[]> thongKeTheoThang();

	// thong ke theo ten nguoi mua
//	@Query("SELECT count(b.id) as SoLuongDonMua , u.name AS UserName FROM Bill b JOIN b.user u WHERE u.id = b.user.id GROUP BY b.user.id")
	@Query(value = "select count(b.id) AS SoDonMua, u.name AS UserName from bill b inner join user u on b.user_id = u.id group by b.user_id", nativeQuery = true)
	List<Object[]> thongKeTheoUserName();
}
