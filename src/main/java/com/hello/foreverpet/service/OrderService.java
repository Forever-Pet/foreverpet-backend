package com.hello.foreverpet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;
import com.hello.foreverpet.domain.entity.BillingInfo;
import com.hello.foreverpet.domain.entity.OrderInfo;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.repository.OrderJpaRepository;
import com.hello.foreverpet.repository.ProductJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;

    private final ProductJpaRepository productJpaRepository;



    public Long createOrder(OrderInfoRequest orderInfoRequest , BillingInfo newBilling , List<Long> ProductNoList) {
        
        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < ProductNoList.size(); i++) {
            if ( productJpaRepository.findById(ProductNoList.get(i)).isPresent() ){
                productList.add(productJpaRepository.findById(ProductNoList.get(i)).get());
            }
        }
        
        OrderInfo newOrder = orderInfoRequest.toEntity();
        newOrder.setBillingNo(newBilling);
        newOrder.setOrderProducts(productList);
        orderJpaRepository.save(newOrder);

        // log.info();
  
        return newOrder.getOrderNo();
    }

    

}
