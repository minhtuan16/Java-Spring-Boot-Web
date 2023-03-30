//package com.example.demo.repository;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.example.demo.entity.Order;
//import com.example.demo.model.dto.OrderDetailDTO;
//import com.example.demo.model.dto.OrderInfoDTO;
//
//import java.util.List;
//
//@Repository
//public interface OrderRepo extends JpaRepository<Order, Long> {
//    @Query(value = "SELECT * FROM orders " +
//            "WHERE id LIKE CONCAT('%',?1,'%') " +
//            "AND receiver_name LIKE CONCAT('%',?2,'%') " +
//            "AND receiver_phone LIKE CONCAT('%',?3,'%') " +
//            "AND status LIKE CONCAT('%',?4,'%') " +
//            "AND book_id LIKE CONCAT('%',?5,'%')", nativeQuery = true)
//    Page<Order> adminGetListOrder(String id, String username, String phone, String status, String product, Pageable pageable);
//
//    @Query(nativeQuery = true, name = "getListOrderOfPersonByStatus")
//    List<OrderInfoDTO> getListOrderOfPersonByStatus(int status, long userId);
//
//    @Query(nativeQuery = true, name = "userGetDetailById")
//    OrderDetailDTO userGetDetailById(long id, long userId);
//
//
//    int countByProductId(String id);
//}
