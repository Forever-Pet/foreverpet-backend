package com.hello.foreverpet.service;


import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.request.OrderRequestBody;
import com.hello.foreverpet.domain.entity.Order;
import com.hello.foreverpet.domain.entity.OrderProduct;
import com.hello.foreverpet.domain.entity.Payment;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.jwt.TokenProvider;
import com.hello.foreverpet.repository.OrderJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;

    private final PaymentService paymentService;

    private final OrderProductService orderProductService;

    private final UserInfoJpaRepository userInfoJpaRepository;

    private final TokenProvider tokenProvider;


    public String createOrder(OrderRequestBody orderRequestBody , HttpHeaders httpHeaders) {
        String result_msg = " 성공 ";

        // 주소 
        Address address = orderRequestBody.getAddress();

        // 핸드폰번호 목록
        String customerPhoneNumber = orderRequestBody.getCustomerPhoneNumber();

        String receiverPhoneNumber = orderRequestBody.getReceiverPhoneNumber();
        
        // 상세품목
        List<OrderProduct> orderproductList = orderProductService.createOrderProductList(orderRequestBody.getOrderProductListRequest());
        
        // 결제정보
        Payment newPayment = paymentService.createPayment(orderRequestBody.getPaymentRequest());

        // 유저정보

        String token = (httpHeaders.get("Authorization").toString()).trim().substring(7);
        // log.info("token = " + token);
        Long userId = Long.valueOf(tokenProvider.getAuthentication(token).getName());
        UserInfo newUser = userInfoJpaRepository.findById(userId).get();

        // 엔티티로 변경
        Order newOrder = Order.builder()
                        .orderProductList(orderproductList)
                        .payment(newPayment)
                        .address(address)
                        .userInfo(newUser)
                        .customerPhoneNumber(customerPhoneNumber)
                        .receiverPhoneNumber(receiverPhoneNumber)
                        .build();

        orderJpaRepository.save(newOrder);

        return result_msg;
    }


}
