package com.hello.foreverpet.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hello.foreverpet.domain.dto.OrderRequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.hello.foreverpet.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Order API", description = "주문 API 입니다")
public class OrderController {
    /*  

        C = createOrder ( 주문 생성 ) - 완
        R = orderList ( 주문 목록 조회 및 검색 조회 ) - 4 검색값
        R = orderDetail ( 주문 단건 조회 ) - 3  param => orderNo
        U = orderUpdate ( 주문 요청 사항 수정 ) - 2 param => orderNo , 수정 값 
        D = orderDelete ( 주문 삭제 ) - 1 Param => orderNo

    */

    private final OrderService orderService;



    @PostMapping("/order")
    @Operation(summary = "주문 등록 ",description = " 결제 , 상품정보확인 후 주문 등록 ")
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequestBody orderRequestBody) {
        // result_msg
        String result_msg = "";

        result_msg = orderService.createOrder(orderRequestBody);

        return ResponseEntity.ok(result_msg);
    }  
} 
