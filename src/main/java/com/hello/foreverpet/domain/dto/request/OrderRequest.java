package com.hello.foreverpet.domain.dto.request;


import java.util.List;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.entity.Payment;
import com.hello.foreverpet.domain.entity.Order;
import com.hello.foreverpet.domain.entity.OrderProduct;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class OrderRequest {

    @NotNull
    private Address address;                        // 주소

    @NotNull
    private Long userNo;                            // 주문한 유저번호

    @NotNull
    private List<OrderProduct> orderProductList;   // 주문한 상품의 개별 리스트

    @NotNull
    private Payment paymentId;                  // 결제 정보


    
    //JSON 형태로 데이터를 요청하는데 Object, 즉, 해당 클래스에 딱 매핑시켜서 출력이 되어야 하기 위함
    public OrderRequest () {

    }

    /**
     * OrderInfo -> Entity 형식으로 변환
     * 
     * @return
    */
    public Order toEntity() {
        return Order.builder().address(this.address)
                .userNo(this.userNo)
                .orderProductList(this.orderProductList)
                .paymentId(this.paymentId)
                .build();
    }


    public OrderRequest( Address address,
       Long userNo, List<OrderProduct> orderproductList, Payment paymentId){
            this.address = address;
            this.userNo = userNo;
            this.orderProductList = orderproductList;
            this.paymentId = paymentId;
    }
}
