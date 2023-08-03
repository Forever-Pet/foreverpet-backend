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
public class Order extends BaseTimeEntity {
    
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
    private Payment paymentId;                  // 결제고유번호

    @NotNull
    @Embedded
    @Column(name = "address")
    private Address address;                        // 주소 

    @NotNull
    @Column(name = "total_price")
    private Long totalPrice;                        // 총 가격


    @NotNull
    @Column(name ="user_no")
    private Long userNo;                            // 주문한 유저번호

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name ="order_process")
    private OrderProcess orderProcess;              // 상품 배송 상황

    @NotNull
    @OneToMany
    @Setter 
    private List<OrderProduct> orderProductList;   // 주문한 상품 상세정보


    @NotNull
    private Long amount;                            // 총 수량 

    @Builder
    public Order(Payment paymentId, Address address,
        Long userNo, List<OrderProduct> orderProductList ) {
            // 총 수량 계산
            Long amount = 0L;
            Long totalPrice = 0L;
            for(int i =0; i<orderProductList.size(); i++ ){
                amount += orderProductList.get(i).getOrderProductAmount();
                totalPrice += orderProductList.get(i).getOrderProductPrice();
            }

            this.totalPrice = totalPrice;
            this.paymentId = paymentId;
            this.address = address;
            this.userNo = userNo;
            this.orderProcess = OrderProcess.ORDER;      // 배송상황 = 주문완료처리로 고정
            this.orderProductList = orderProductList;
            this.amount = amount;
    }

}