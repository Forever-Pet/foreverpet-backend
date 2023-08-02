package com.hello.foreverpet.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_product")
@Getter
public class OrderProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long orderProductId;                    // 리스트 id 

    @NotNull
    @Column(name = "order_product_amount")
    @Setter
    private Long orderProductAmount;                    // 상품수량

    @NotNull
    @Column(name = "order_product_price")
    @Setter
    private Long orderProductPrice;                     // 가격

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @Setter
    private Product orderProduct;                   // 상품정보

    @Builder
    public OrderProduct(Long orderProductAmount,
    Long orderProductPrice, Product orderProduct) {

        this.orderProductAmount = orderProductAmount;
        this.orderProductPrice = orderProductPrice;
        this.orderProduct = orderProduct;

    }
}
