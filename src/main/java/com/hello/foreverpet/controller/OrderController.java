package com.hello.foreverpet.controller;


import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.hello.foreverpet.domain.dto.request.OrderRequestBody;
import com.hello.foreverpet.domain.dto.response.OrderResponse;
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
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequestBody orderRequestBody , @RequestHeader HttpHeaders header ) {

        return ResponseEntity.ok(orderService.createOrder( orderRequestBody , header ));
    }  

    @GetMapping("/order")
    @Operation(summary = "주문내역 보기 ",description = " 사용자의 주문 내역 확인 ")
    public ResponseEntity<List<OrderResponse>> findOrderProductByUserId( @RequestHeader HttpHeaders header ) {

        return ResponseEntity.ok(orderService.findOrderProductByUserId(header));
    }

    @PutMapping("/ordercancle/{orderId}")
    @Operation(summary = "주문취소 ",description = " 사용자의 주문 내역 취소 ")
    public ResponseEntity<String> OrderProcessCancelByOrderId(@PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.OrderProcessCancelByOrderId(orderId));
    }

} 
