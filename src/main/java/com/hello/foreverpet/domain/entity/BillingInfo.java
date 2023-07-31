package com.hello.foreverpet.domain.entity;

import com.hello.foreverpet.auditing.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "billing_info")
@Getter
public class BillingInfo extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_no")
    private Long billingNo;             // 결제 고유 번호

    @NotNull
    @Column(name = "billing_nm")
    private String billingNm;           // 결제 리턴 번호 ( PG사 / UNIQUE / uuid_날짜 )

    @NotNull
    @Column(name = "payment_gateway")
    private String paymentGateway;      // PG사

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;       // 결제방법

    @Builder
    public BillingInfo(Long billingNo, String billingNm,
        String paymentGateway,String paymentMethod) {

            this.billingNo = billingNo;
            this.billingNm = billingNm;
            this.paymentGateway = paymentGateway;
            this.paymentMethod = paymentMethod;

    }


    
}
