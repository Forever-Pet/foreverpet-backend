package com.hello.foreverpet.domain.dto.request;


import com.hello.foreverpet.domain.entity.Payment;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotNull
    @Column(name = "payment_name")
    private String paymentName;             // 날짜정보_UUID 

    @NotNull
    @Column(name = "payment_gateway")
    private String paymentGateway;          // PG사 정보

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;           // 결제방법

    
    //JSON 형태로 데이터를 요청하는데 Object, 즉, 해당 클래스에 딱 매핑시켜서 출력이 되어야 하기 위함
    public PaymentRequest () {

    }

    /**
     * PaymentInfo -> Entity 형식으로 변환
     * 
     * @return
    */
    public Payment toEntity() {
        return Payment.builder().paymentName(this.paymentName)
                .paymentGateway(this.paymentGateway)
                .paymentMethod(this.paymentMethod)
                .build();
    }


    public PaymentRequest( String paymentName, String paymentGateway,
       String paymentMethod){
            this.paymentName = paymentName;
            this.paymentGateway = paymentGateway;
            this.paymentMethod = paymentMethod;
    }
}
