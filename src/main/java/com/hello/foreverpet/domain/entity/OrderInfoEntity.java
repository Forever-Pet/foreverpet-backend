package com.hello.foreverpet.domain.entity;

import com.hello.foreverpet.domain.dto.Address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Table(name = "order_info")
@Getter
public class OrderInfoEntity {
    // @NotNull 의 장점 = 데이터베이스에 SQL 쿼리를 보내기 전에 예외가 발생한다 즉 DB에 값이 넘어가기 전에 예외가 발생한다는 것
    // !
    // Nullable 은 DB에 값이 넘어갈 수도 있다고 합니다.
    // 자세한 사항은 https://kafcamus.tistory.com/15 확인하시면 됩니다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "order_no")
    private Long order_no; // 주문번호

    private Long order_payment_no; // 결제번호

    private Address address;

    private String payment_cd; // 결제방법

    private String user_no; // 주문한 유저번호 ( FK 설정해야함 )
    // 비회원주문도 가능 -> 비회원주문할때 유저번호 하나 생성하면 될듯
    // userType같은 컬럼을 만들어서 소셜회원 ( N , K , G ), 회원, 비회원으로 나누는 방안

    private String contents; // 요청사항
    
    private String memo; // 메모

    private Long order_process; // 상품 배송 상황

    private Long purchase_no; // 상품번호 ( FK 설정해야함 )

}