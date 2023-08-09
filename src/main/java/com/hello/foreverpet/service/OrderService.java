package com.hello.foreverpet.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.OrderRequestBody;
import com.hello.foreverpet.domain.entity.Order;
import com.hello.foreverpet.domain.entity.OrderProduct;
import com.hello.foreverpet.domain.entity.Payment;
import com.hello.foreverpet.domain.entity.UserInfo;
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


    public String createOrder(OrderRequestBody orderInfoRequest) {
        String result_msg = " 성공 ";

        // 주소 
        Address address = orderInfoRequest.getOrderRequest().getAddress();

        // 핸드폰번호 목록
        String customerPhoneNumber = orderInfoRequest.getOrderRequest().getCustomerPhoneNumber();

        String receiverPhoneNumber = orderInfoRequest.getOrderRequest().getReceiverPhoneNumber();
        
        // 상세품목
        List<OrderProduct> orderproductList = orderProductService.createOrderProductList(orderInfoRequest.getOrderProductListRequest());
        
        // 결제정보
        Payment newPayment = paymentService.createPayment(orderInfoRequest.getPaymentRequest());

        // 유저정보
        log.info(orderInfoRequest.getUserNo().toString());
        UserInfo newUser = userInfoJpaRepository.findById(orderInfoRequest.getUserNo()).get();

        // 엔티티로 변경
        Order newOrder = Order.builder()
                        .orderProductList(orderproductList)
                        .paymentId(newPayment)
                        .address(address)
                        .userInfo(newUser)
                        .customerPhoneNumber(customerPhoneNumber)
                        .receiverPhoneNumber(receiverPhoneNumber)
                        .build();

        orderJpaRepository.save(newOrder);

        return result_msg;
    }


}
