package com.hello.foreverpet.domain.entity;

import com.hello.foreverpet.auditing.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "payment_info")
@Getter
public class Payment extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;             // 결제 고유 번호

    @NotNull
    @Column(name = "payment_name", unique=true)
    private String paymentName;         // 결제 리턴 번호 ( uuid_날짜 )

    @NotNull
    @Column(name = "payment_gateway")
    private String paymentGateway;      // PG사

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;       // 결제방법


    @OneToOne(mappedBy = "payment")
    private Order order;                // 주문정보 확인 


    @Builder
    public Payment(Long paymentId, String paymentName,
        String paymentGateway,String paymentMethod) {

            this.paymentId = paymentId;
            this.paymentName = paymentName;
            this.paymentGateway = paymentGateway;
            this.paymentMethod = paymentMethod;

    }


    
}
