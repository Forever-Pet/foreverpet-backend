package com.hello.foreverpet.domain.entity;

import java.util.List;

import com.hello.foreverpet.auditing.BaseTimeEntity;
import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.OrderProcess;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "order_info")
@Getter
public class OrderInfo extends BaseTimeEntity {
    
    // @NotNull 의 장점 = 데이터베이스에 SQL 쿼리를 보내기 전에 예외가 발생한다 즉 DB에 값이 넘어가기 전에 예외가 발생한다는 것
    // !
    // Nullable 은 DB에 값이 넘어갈 수도 있다고 합니다.
    // 자세한 사항은 https://kafcamus.tistory.com/15 확인하시면 됩니다.


    /*
     * 주문번호 관련 사항
     * -1 ) DB에서 검증해서 보내준다 --> 트랜잭션 필요 ( 동시 요청 시 )
     * -2 ) 프론트에서 uuid 발생 --> 
     * -3 ) 구매수량 보낼 때 같이 보낸다 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;                           // 주문번호

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    @Setter
    private PaymentInfo paymentId;                  // 결제고유번호

    @NotNull
    @Embedded
    @Column(name = "address")
    private Address address;                        // 주소 

    @NotNull
    @Column(name = "total_price")
    private Long totalPrice;                        // 총 가격


    @NotNull
    @Column(name ="user_no")
    private Long userNo;                            // 주문한 유저번호 ( fk 설정할 경우 유저정보 조회 시 데이터 과다 ) // Long vs Entity 맵핑 관계 조회 데이터 분석 

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name ="order_process")
    private OrderProcess orderProcess;              // 상품 배송 상황

    @NotNull
    @OneToMany
    @Setter 
    // orderproductlist
    private List<OrderProduct> orderProductList;   // 상품번호 ( FK 설정 , 한개의 주문정보에 많은 상품이 있을 수 있음 )  // querydsl patch join


    @NotNull
    private Long amount;                            // 총 수량 

    @Builder
    public OrderInfo(Long orderId, PaymentInfo paymentId, Address address,
        Long userNo, List<OrderProduct> orderProductList ) {
            // 총 수량 계산
            Long amount = 0L;
            Long totalPrice = 0L;
            for(int i =0; i<orderProductList.size(); i++ ){
                amount = amount + orderProductList.get(i).getOrderProductAmount();
                totalPrice = totalPrice + orderProductList.get(i).getOrderProductPrice();
            }
            this.totalPrice = totalPrice;
            this.orderId = orderId;
            this.paymentId = paymentId;
            this.address = address;
            this.userNo = userNo;
            this.orderProcess = OrderProcess.ORDER;      // 배송상황 = 주문완료처리로 고정
            this.orderProductList = orderProductList;
            this.amount = amount;
    }

}