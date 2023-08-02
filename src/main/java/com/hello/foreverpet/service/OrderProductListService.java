package com.hello.foreverpet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.OrderInfoRequest;
import com.hello.foreverpet.domain.dto.request.OrderProductListRequest;
import com.hello.foreverpet.domain.entity.BillingInfo;
import com.hello.foreverpet.domain.entity.OrderInfo;
import com.hello.foreverpet.domain.entity.OrderProductList;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.repository.OrderProductListJpaRepository;
import com.hello.foreverpet.repository.ProductJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProductListService {

    private final ProductJpaRepository productJpaRepository;

    private final OrderProductListJpaRepository orderProductListJpaRepository;

    public List<OrderProductList> createOrderProductList (List<Long> ProductNoList) {

        List<OrderProductList> orderproductList = new ArrayList<>();

        Map<Long, Integer> ProductIDmap = new HashMap();

        // 중복사항 처리
        for ( int i = 0 ; i< ProductNoList.size(); i++ ){
            if(ProductIDmap.containsKey(ProductNoList.get(i))){
                ProductIDmap.put(ProductNoList.get(i), ProductIDmap.get(ProductNoList.get(i)) + 1 );
            } else {
                ProductIDmap.put(ProductNoList.get(i), 1);
            }
        }

        for( Map.Entry<Long, Integer> elem : ProductIDmap.entrySet() ){
            
            if ( productJpaRepository.findById(elem.getKey()).isPresent() ) {

                // 상품정보
                Product orderproduct = productJpaRepository.findById(elem.getKey()).get();
                // 상품 ID
                Long orderProductId = elem.getKey();
                // 수량 
                Long amount = (long) elem.getValue();
                // 상품 가격
                Long price = amount * orderproduct.getProductPrice();
                
                // Request 생성 
                OrderProductListRequest orderProductListRequest = new OrderProductListRequest(orderProductId, amount, price, orderproduct);
                
                // 엔티티방식으로 변환 
                OrderProductList newOrderProduct = orderProductListRequest.toEntity();
                
                // 저장 후 리스트에 적재 
                orderproductList.add(orderProductListJpaRepository.save(newOrderProduct));
            }
        }
        
        return orderproductList;
    }

}