package com.hello.foreverpet.domain.dto.request;


import com.hello.foreverpet.domain.entity.BillingInfo;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillingInfoRequest {

    @NotNull
    @Column(name = "billing_nm")
    private String billingNm;

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
        return BillingInfo.builder().billingNm(this.billingNm)
                .paymentGateway(this.paymentGateway)
                .paymentMethod(this.paymentMethod)
                .build();
    }


    public BillingInfoRequest( String billingNm, String paymentGateway,
       String paymentMethod){
            this.billingNm = billingNm;
            this.paymentGateway = paymentGateway;
            this.paymentMethod = paymentMethod;
    }
}
