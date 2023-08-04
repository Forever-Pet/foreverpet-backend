package com.hello.foreverpet.service;


import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.OrderRequest;
import com.hello.foreverpet.domain.entity.Order;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.repository.OrderJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;

    public Long createOrder(OrderRequest orderInfoRequest) {

        // 엔티티로 변경         
        Order newOrder = orderInfoRequest.toEntity();

        orderJpaRepository.save(newOrder);
  
        return newOrder.getOrderId();
    }

    

}
