package com.hello.foreverpet.service;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.OrderProcess;
import com.hello.foreverpet.domain.dto.request.OrderRequestBody;
import com.hello.foreverpet.domain.dto.response.OrderResponse;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.entity.Order;
import com.hello.foreverpet.domain.entity.OrderProduct;
import com.hello.foreverpet.domain.entity.Payment;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.domain.exception.user.OrderNotFoundException;
import com.hello.foreverpet.domain.exception.user.ProductNotFoundException;
import com.hello.foreverpet.handler.ErrorCode;
import com.hello.foreverpet.jwt.TokenProvider;
import com.hello.foreverpet.repository.CustomOrderRepository;
import com.hello.foreverpet.repository.OrderJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import com.mysql.cj.protocol.x.Notice;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;

    private final CustomOrderRepository CustomOrderRepository;

    private final PaymentService paymentService;

    private final OrderProductService orderProductService;

    private final UserInfoJpaRepository userInfoJpaRepository;

    private final TokenProvider tokenProvider;


    public String createOrder(OrderRequestBody orderRequestBody , HttpHeaders httpHeaders) {

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

        Long orderId = orderJpaRepository.save(newOrder).getOrderId();

        

        return "성공";
    }

    public List<OrderResponse> findOrderProductByUserId (HttpHeaders httpHeaders) {

        String token = (httpHeaders.get("Authorization").toString()).trim().substring(7);
        // log.info("token = " + token);
        Long userId = Long.valueOf(tokenProvider.getAuthentication(token).getName());

        List<OrderResponse> orderResponses = CustomOrderRepository.findOrderProductByUserId(userId);


        return orderResponses;
    }

    
    @Transactional
    public String OrderProcessCancelByOrderId(Long orderId) {
        log.info(orderId.toString());
        
        Order order = orderJpaRepository.findById(orderId).orElseThrow( () -> new OrderNotFoundException(ErrorCode.ORDER_FOUND_ERROR) );
        
        order.setOrderProcess(OrderProcess.CANCLE); 

        return " 성공 ";
}


}
