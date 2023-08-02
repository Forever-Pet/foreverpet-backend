package com.hello.foreverpet.domain.dto;

import java.util.List;

import com.hello.foreverpet.domain.dto.request.BillingInfoRequest;
import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private BillingInfoRequest billingInfoRequest;      // 결제정보

    private OrderInfoRequest orderInfoRequest;          // 주문정보

    private List<Long> productNoList;                   // 상품 번호 목록

    public CreateOrderRequest() {

    }

}
