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
public class OrderProductList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_list_id")
    private Long orderProductListId;                    // 리스트 id 

    @NotNull
    @Column(name = "order_product_id")
    @Setter
    private Long orderProductId;                        // 상품번호

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
    public OrderProductList(Long orderProductListId, Long orderProductId, Long orderProductAmount,
    Long orderProductPrice, Product orderProduct) {

        this.orderProductListId = orderProductListId;
        this.orderProductId = orderProductId;
        this.orderProductAmount = orderProductAmount;
        this.orderProductPrice = orderProductPrice;
        this.orderProduct = orderProduct;

    }
}
