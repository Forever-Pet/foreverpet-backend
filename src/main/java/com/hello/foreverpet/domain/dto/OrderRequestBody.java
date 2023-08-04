package com.hello.foreverpet.domain.dto;

import com.hello.foreverpet.domain.dto.request.PaymentRequest;

import java.util.List;

import com.hello.foreverpet.domain.dto.request.OrderRequest;
import com.hello.foreverpet.domain.dto.request.OrderProductListRequest;

import lombok.Data;

@Data
public class OrderRequestBody {

    private PaymentRequest paymentRequest;      // 결제정보

    private OrderRequest orderRequest;          // 주문정보

    private List<OrderProductListRequest> OrderProductListRequest;                 // 상품 목록

    private Long userNo;

    public OrderRequestBody() {

    }

}
