package com.hello.foreverpet.domain.dto;

import com.hello.foreverpet.domain.dto.request.PaymentInfoRequest;

import java.util.List;

import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;
import com.hello.foreverpet.domain.dto.request.OrderProductRequest;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private PaymentInfoRequest paymentInfoRequest;      // 결제정보

    private OrderInfoRequest orderInfoRequest;          // 주문정보

    private List<OrderProductRequest> orderProductRequest;                 // 상품 번호 목록

    public CreateOrderRequest() {

    }

}
