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
    @Column(name = "billing_id")
    private Long billingId;             // 결제 고유 번호

    @NotNull
    @Column(name = "billing_name")
    private String billingName;         // 결제 리턴 번호 ( uuid_날짜 )

    @NotNull
    @Column(name = "payment_gateway")
    private String paymentGateway;      // PG사

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;       // 결제방법

    @Builder
    public BillingInfo(Long billingId, String billingName,
        String paymentGateway,String paymentMethod) {

            this.billingId = billingId;
            this.billingName = billingName;
            this.paymentGateway = paymentGateway;
            this.paymentMethod = paymentMethod;

    }


    
}
