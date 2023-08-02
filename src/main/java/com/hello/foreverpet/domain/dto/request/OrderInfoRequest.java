package com.hello.foreverpet.domain.dto.request;


import java.util.List;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.entity.BillingInfo;
import com.hello.foreverpet.domain.entity.OrderInfo;
import com.hello.foreverpet.domain.entity.OrderProductList;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class OrderInfoRequest {

    @NotNull
    private Address address;                        // 주소

    @NotNull
    private Long userNo;                            // 주문한 유저번호

    @NotNull
    private List<OrderProductList> orderproducts;   // 주문한 상품의 개별 리스트

    @NotNull
    private BillingInfo billingId;                  // 주문 정보


    
    //JSON 형태로 데이터를 요청하는데 Object, 즉, 해당 클래스에 딱 매핑시켜서 출력이 되어야 하기 위함
    public OrderInfoRequest () {

    }

    /**
     * OrderInfo -> Entity 형식으로 변환
     * 
     * @return
    */
    public OrderInfo toEntity() {
        return OrderInfo.builder().address(this.address)
                .userNo(this.userNo)
                .orderProducts(this.orderproducts)
                .billingId(this.billingId)
                .build();
    }


    public OrderInfoRequest( Address address,
       Long userNo, List<OrderProductList> orderproducts, BillingInfo billingId){
            this.address = address;
            this.userNo = userNo;
            this.orderproducts = orderproducts;
            this.billingId = billingId;
    }
}
