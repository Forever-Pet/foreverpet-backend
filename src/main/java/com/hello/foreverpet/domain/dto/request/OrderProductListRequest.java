package com.hello.foreverpet.domain.dto.request;


import com.hello.foreverpet.domain.entity.OrderProductList;
import com.hello.foreverpet.domain.entity.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderProductListRequest {

    @NotNull
    private Long orderProductId;                        // 상품번호

    @NotNull
    private Long orderProductAmount;                    // 상품수량

    @NotNull
    private Long orderProductPrice;                     // 가격

    @NotNull
    private Product orderProductList;             // 상품정보D


    /**
     * OrderProductList -> Entity 형식으로 변환
     * 
     * @return
    */
    public OrderProductList toEntity() {
        return OrderProductList.builder().orderProductId(this.orderProductId)
                .orderProductAmount(this.orderProductAmount)
                .orderProductPrice(this.orderProductPrice)
                .orderProductList(this.orderProductList)
                .build();
    }


    public OrderProductListRequest( Long orderProductId, Long orderProductAmount,
     Long orderProductPrice, Product orderProductList){
            this.orderProductId = orderProductId;
            this.orderProductAmount = orderProductAmount;
            this.orderProductPrice = orderProductPrice;
            this.orderProductList = orderProductList;
    }
}