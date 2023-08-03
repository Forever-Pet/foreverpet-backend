package com.hello.foreverpet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hello.foreverpet.domain.dto.request.OrderProductListRequest;
import com.hello.foreverpet.domain.entity.OrderProduct;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.repository.OrderProductListJpaRepository;
import com.hello.foreverpet.repository.ProductJpaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProductService {

    private final ProductJpaRepository productJpaRepository;

    private final OrderProductListJpaRepository orderProductListJpaRepository;

    public List<OrderProduct> createOrderProductList (List<OrderProductListRequest> orderProductRequest) {

        // return 값 
        List<OrderProduct> orderproductList = new ArrayList<>();

        // 중복사항 처리 map
        Map<Long, Integer> productIdMap = new HashMap();

        for (int i =0; i< orderProductRequest.size(); i++ ) {      
            if ( productIdMap.get(orderProductRequest.get(i).getOrderProductId())!= null ) {
                // 기존값이 있는 경우 
                productIdMap.put(orderProductRequest.get(i).getOrderProductId(), 
                    productIdMap.get(orderProductRequest.get(i).getOrderProductId()) + orderProductRequest.get(i).getOrderProductAmount() );
            } else {
                // 기존값이 없는 경우
                productIdMap.put(orderProductRequest.get(i).getOrderProductId(), 
                    orderProductRequest.get(i).getOrderProductAmount() );

            }
         }

        // List 적재
        for( Map.Entry<Long, Integer> elem : productIdMap.entrySet() ){
            

            // 익셉션 처리 
            Product getOrderProductInfo = new Product();
            getOrderProductInfo = productJpaRepository.findById(elem.getKey())
                                    .orElseThrow(IllegalArgumentException::new);
                
                // 수량 
                Long amount = (long) elem.getValue();

                // 상품 가격
                Long price = amount * getOrderProductInfo.getProductPrice();
                
                // 생성
                OrderProduct orderProduct = new OrderProduct(amount, price, getOrderProductInfo);

                // 저장 후 리스트에 적재 
                orderproductList.add(orderProductListJpaRepository.save(orderProduct));
            
        }

        return orderproductList;
    }

}