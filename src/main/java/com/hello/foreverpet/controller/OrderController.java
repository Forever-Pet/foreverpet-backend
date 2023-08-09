package com.hello.foreverpet.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hello.foreverpet.domain.dto.OrderRequestBody;
import com.hello.foreverpet.domain.dto.request.OrderRequest;
import com.hello.foreverpet.domain.entity.Payment;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.repository.OrderJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import com.hello.foreverpet.domain.entity.Order;
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

    private final UserInfoJpaRepository userInfoJpaRepository;

    private final OrderJpaRepository orderJpaRepository;


    @PostMapping("/order")
    @Operation(summary = "주문 등록 ",description = " 결제 , 상품정보확인 후 주문 등록 ")
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequestBody orderRequestBody) {
        // result_msg
        String result_msg = "";

        // 주문정보 엔티티 생성  
        OrderRequest orderRequest = orderRequestBody.getOrderRequest();

        // 주문상품 개별 저장
        try {
            List<OrderProduct> orderproductList = orderProductService.createOrderProductList(orderRequestBody.getOrderProductListRequest());
            orderRequest.setOrderProductList(orderproductList);
        } catch (Exception e) {
            result_msg = " 존재하지 않는 상품번호 입니다. ";
            return ResponseEntity.ok(result_msg);
        }

        // 결제정보 저장
        try {
            Payment newPayment = paymentService.createPayment(orderRequestBody.getPaymentRequest());
            orderRequest.setPaymentId(newPayment);
        } catch (Exception e) {
            result_msg = " 중복된 PaymentName 입니다. ";
            return ResponseEntity.ok(result_msg);
        }

        // 주문정보 저장
        try {
            UserInfo userInfo = userInfoJpaRepository.findById(orderRequestBody.getUserNo()).get();
            orderRequest.setUserInfo(userInfo);
        } catch (Exception e) {
             result_msg = " 존재하지 않는 userNo 입니다. ";
            return ResponseEntity.ok(result_msg);
        }
        Long orderId = orderService.createOrder(orderRequest);

        Order neworder = orderJpaRepository.findById(orderId).get();
        log.info (neworder.toString());

        return ResponseEntity.ok("성공");
    }  
} 
