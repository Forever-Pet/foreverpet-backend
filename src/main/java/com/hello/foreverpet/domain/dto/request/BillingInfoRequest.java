package com.hello.foreverpet.domain.dto.request;


import com.hello.foreverpet.domain.entity.BillingInfo;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillingInfoRequest {

    @NotNull
    @Column(name = "billing_name")
    private String billingName;

    @NotNull
    @Column(name = "payment_gateway")
    private String paymentGateway;

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;

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
