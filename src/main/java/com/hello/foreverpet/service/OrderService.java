package com.hello.foreverpet.service;


import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;
import com.hello.foreverpet.domain.entity.OrderInfo;
import com.hello.foreverpet.repository.OrderJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;

    public Long createOrder(OrderInfoRequest orderInfoRequest) {

        // 엔티티로 변경         
        OrderInfo newOrder = orderInfoRequest.toEntity();

        orderJpaRepository.save(newOrder);
  
        return newOrder.getOrderId();
    }

    

}
