package com.hello.foreverpet.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hello.foreverpet.domain.dto.CreateOrderRequest;
import com.hello.foreverpet.domain.entity.BillingInfo;
import com.hello.foreverpet.domain.entity.OrderProductList;
import com.hello.foreverpet.service.BillingService;
import com.hello.foreverpet.service.OrderProductListService;
import com.hello.foreverpet.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {
    /*  

        C = createOrder ( 주문 생성 ) - 완
        R = orderList ( 주문 목록 조회 및 검색 조회 ) - 4 검색값
        R = orderDetail ( 주문 단건 조회 ) - 3  param => orderNo
        U = orderUpdate ( 주문 요청 사항 수정 ) - 2 param => orderNo , 수정 값 
        D = orderDelete ( 주문 삭제 ) - 1 Param => orderNo
        + 주문완료시 이메일발송 ? 

    */

    private final OrderService orderService;

    private final BillingService billingService;

    private final OrderProductListService orderProductListService;


    @PostMapping("/order")
    public ResponseEntity<Long> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest ) {

        List<OrderProductList> orderproductList = orderProductListService.createOrderProductList(createOrderRequest.getProductNoList());

        BillingInfo newBilling = billingService.createBilling(createOrderRequest.getBillingInfoRequest());

        Long orderNo = orderService.createOrder(createOrderRequest.getOrderInfoRequest(), newBilling, orderproductList);

        return ResponseEntity.ok(orderNo);
    }  
} 
