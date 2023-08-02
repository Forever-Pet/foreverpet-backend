package com.hello.foreverpet.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hello.foreverpet.domain.dto.CreateOrderRequest;
import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;
import com.hello.foreverpet.domain.entity.PaymentInfo;
import com.hello.foreverpet.domain.entity.OrderProduct;
import com.hello.foreverpet.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;

import com.hello.foreverpet.service.OrderProductService;
import com.hello.foreverpet.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
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

    private final PaymentService paymentService;

    private final OrderProductService orderProductService;


    @PostMapping("/order")
    @Operation(summary = "주문 등록 ",description = " 결제 , 상품정보확인 후 주문 등록 ")
    public ResponseEntity<Long> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {

        // 주문상품 개별 저장
        List<OrderProduct> orderproductList = orderProductService.createOrderProductList(createOrderRequest.getOrderProductRequest());

        // 결제정보 저장
        PaymentInfo newPayment = paymentService.createPayment(createOrderRequest.getPaymentInfoRequest());

        // 주문정보 엔티티 생성  
        OrderInfoRequest orderInfoRequest = createOrderRequest.getOrderInfoRequest();
        orderInfoRequest.setPaymentId(newPayment);
        orderInfoRequest.setOrderProductList(orderproductList);

        // 주문정보 저장
        Long orderNo = orderService.createOrder(orderInfoRequest);

        return ResponseEntity.ok(orderNo);
    }  
} 
