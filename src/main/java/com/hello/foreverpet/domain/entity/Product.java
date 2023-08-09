package com.hello.foreverpet.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.hello.foreverpet.auditing.BaseTimeEntity;
import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotNull
    private String productName;
    @NotNull
    @Column(length = 500)
    private String productDescription;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Categories categories;
    @NotNull
    private Long productPrice;

    private Long numberOfSold;

    private String productImage;

    private String brandName;

    // 회원과 찜목록 연관관계 매핑 필요
    // 장바구니 프론트에서 처리 여부 화~수 에 결정.
//    @ManyToOne
//    private Member member;

    //주문과의 연관관계 매핑 필요

    @ManyToMany
    private List<Order> orders = new ArrayList<>();

    //재고처리를 한다면
//    private Long stock;

    // 재고처리를 한다면 주문시 재고확인 작업이 먼저 필요하다.

    @Builder
    public Product(String productName, String productDescription, Categories categories, Long productPrice,
                   String productImage, String brandName) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.categories = categories;
        this.productPrice = productPrice;
        this.numberOfSold = 0L;
        this.productImage = productImage;
        this.brandName = brandName;
    }

    public Product updateProductByUpdateRequest(UpdateProductRequest updateProductRequest) {
        this.productName = updateProductRequest.getProductName();
        this.productDescription = updateProductRequest.getProductDescription();
        this.categories = Categories.valueOf(updateProductRequest.getCategories());
        this.productPrice = updateProductRequest.getProductPrice();
        this.productImage = updateProductRequest.getProductImage();
        this.brandName = updateProductRequest.getBrandName();
        return this;
    }

    // 주문이 들어올때
    // 주문 상품들에 대한 List 가 있을것이다.
    // numberOfSold 를 ++ 해주고 order 를 같이 지정해줄수 있나 ?

    public void soldProducts(Order orderInfo) {
        // 주문시 판매수량 ++
//        this.numberOfSold++;
        // 주문시 orderInfo add
//        this.orders.add(orderInfo);
//    }

    //주문취소시 판매수량 -
//    public void cancelOrder() {
//        if (this.numberOfSold > 0) {
//            this.numberOfSold--;
//        }
//    }

    // 재고 확인 메서드
//    public boolean checkStock(Long wantOrderQuantity) {
//        if (this.stock > wantOrderQuantity) {
//            return true;
//        } else {
//            throw new IllegalArgumentException("주문 가능한 수량은" + this.stock + " 개 입니다.");
//        }
//    }
}
}