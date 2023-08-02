package com.hello.foreverpet.domain.dto.request;


import com.hello.foreverpet.domain.entity.BillingInfo;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillingInfoRequest {

    @NotNull
    @Column(name = "billing_name")
    private String billingName;             // 날짜정보_UUID 

    @NotNull
    @Column(name = "payment_gateway")
    private String paymentGateway;          // PG사 정보

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;           // 결제방법

    
    //JSON 형태로 데이터를 요청하는데 Object, 즉, 해당 클래스에 딱 매핑시켜서 출력이 되어야 하기 위함
    public BillingInfoRequest () {

    }

    /**
     * BillingInfo -> Entity 형식으로 변환
     * 
     * @return
    */
    public BillingInfo toEntity() {
        return BillingInfo.builder().billingName(this.billingName)
                .paymentGateway(this.paymentGateway)
                .paymentMethod(this.paymentMethod)
                .build();
    }


    public BillingInfoRequest( String billingName, String paymentGateway,
       String paymentMethod){
            this.billingName = billingName;
            this.paymentGateway = paymentGateway;
            this.paymentMethod = paymentMethod;
    }
}
