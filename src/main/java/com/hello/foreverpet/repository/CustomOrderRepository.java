package com.hello.foreverpet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hello.foreverpet.domain.dto.response.OrderResponse;
import com.hello.foreverpet.domain.entity.Order;

@Repository
public interface CustomOrderRepository extends JpaRepository<Order, String> {
    
    @Query(value = "select " +
            "oi.order_id as orderId, " +
            "oi.order_process as orderProcess, " +
            "p.product_image as productImage, " +
            "p.product_name as productName, " + 
            "p.product_price as productPrice, " + 
            "p.product_price * op.order_product_amount as productTotalPrice, " + 
            "op.order_product_amount as productAmount " +
            "from " + 
            "order_info oi " + 
            "inner join order_info_order_product_list opl " +
            "on " + 
            "oi.order_id = opl.order_order_id " + 
            "inner join order_product op " + 
            "on opl.order_product_list_order_product_id = op.order_product_id " + 
            "inner join product p " + 
            "on op.order_product_product_id = p.product_id " + 
            "where oi.user_no = :userNo " , nativeQuery = true)
    List<OrderResponse> findOrderProductByUserId (@Param("userNo") Long userNo);

}
