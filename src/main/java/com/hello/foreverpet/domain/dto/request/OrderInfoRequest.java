package com.hello.foreverpet.domain.dto.request;


import java.util.List;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.entity.OrderInfo;
import com.hello.foreverpet.domain.entity.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class OrderInfoRequest {

    @NotNull
    private Address address;    // 주소

    @NotNull
    private Long userNo; // 주문한 유저번호

    @NotNull
    private Long price; // 가격


    /**
     * OrderInfo -> Entity 형식으로 변환
     * 
     * @return
    */
    public OrderInfo toEntity() {
        return OrderInfo.builder().address(this.address)
                .userNo(this.userNo)
                .build();
    }


    public OrderInfoRequest( Address address,
       Long UserNo, List<Product>purchaseNo){
            this.address = address;
            this.userNo = userNo;
    }
}
