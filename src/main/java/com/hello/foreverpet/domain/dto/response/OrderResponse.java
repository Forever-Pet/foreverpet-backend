package com.hello.foreverpet.domain.dto.response;

import com.hello.foreverpet.domain.dto.OrderProcess;


public interface OrderResponse {
    
    Long getProductTotalPrice ();               // 총 주문가격
    String getProductImage ();                  // 상품이미지
    String getProductName ();                   // 상품이름
    Long getOrderId ();                         // 주문번호
    OrderProcess getOrderProcess ();            // 주문상황
    Long getProductAmount ();                   // 총 주문수량
    Long getProductPrice ();                    // 개별 가격

}
