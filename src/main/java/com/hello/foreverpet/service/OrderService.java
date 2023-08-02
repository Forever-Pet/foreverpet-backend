package com.hello.foreverpet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;
import com.hello.foreverpet.domain.entity.BillingInfo;
import com.hello.foreverpet.domain.entity.OrderInfo;
import com.hello.foreverpet.domain.entity.OrderProductList;
import com.hello.foreverpet.repository.OrderJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;

    public Long createOrder(OrderInfoRequest orderInfoRequest, BillingInfo newBilling, List<OrderProductList> orderProductList) {

        // 엔티티로 변경         
        OrderInfo newOrder = orderInfoRequest.toEntity();
        newOrder.setBillingId(newBilling);
        newOrder.setOrderProducts(orderProductList);
        orderJpaRepository.save(newOrder);

        // log.info();
  
        return newOrder.getOrderId();
    }

    

}
