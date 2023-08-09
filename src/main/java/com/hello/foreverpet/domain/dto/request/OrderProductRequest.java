package com.hello.foreverpet.domain.dto.request;


import com.hello.foreverpet.domain.entity.OrderProduct;
import com.hello.foreverpet.domain.entity.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

// 사용 중단 
@Data
public class OrderProductRequest {

    @NotNull
    private Long orderProductAmount;                    // 상품수량

    @NotNull
    private Long orderProductPrice;                     // 가격

    @NotNull
    private Product orderProduct;                       // 상품정보


    /**
     * OrderProductList -> Entity 형식으로 변환
     * 
     * @return
    */
    public OrderProduct toEntity() {
        return OrderProduct.builder().orderProductAmount(this.orderProductAmount)
                .orderProductPrice(this.orderProductPrice)
                .orderProduct(this.orderProduct)
                .build();
    }


    public OrderProductRequest( Long orderProductAmount,
     Long orderProductPrice, Product orderProduct){
            this.orderProductAmount = orderProductAmount;
            this.orderProductPrice = orderProductPrice;
            this.orderProduct = orderProduct;
    }
}