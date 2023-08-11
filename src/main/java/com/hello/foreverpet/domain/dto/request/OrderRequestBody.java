package com.hello.foreverpet.domain.dto.request;

import java.util.List;

import com.hello.foreverpet.domain.dto.Address;

import lombok.Data;

@Data
public class OrderRequestBody {

    private PaymentRequest paymentRequest;      // 결제정보

    private Address address;

    private String customerPhoneNumber;

    private String receiverPhoneNumber;

    private List<OrderProductRequest> OrderProductListRequest;                 // 상품 목록

    public OrderRequestBody() {

    }

}
