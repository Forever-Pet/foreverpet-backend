package com.hello.foreverpet.domain.dto.request;


import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.entity.Order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class OrderRequest {

    @NotNull
    private Address address;                        // 주소

    @NotNull
    private String customerPhoneNumber;             // 주문자 핸드폰 번호

    @NotNull
    private String receiverPhoneNumber;             // 수령인 핸드폰 번호

    
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
                .customerPhoneNumber(this.customerPhoneNumber)
                .receiverPhoneNumber(this.receiverPhoneNumber)
                .build();
    }


    public OrderRequest( Address address, String customerPhoneNumber , String receiverPhoneNumber ){
            this.address = address;
            this.customerPhoneNumber = customerPhoneNumber;
            this.receiverPhoneNumber = receiverPhoneNumber;
    }
}
