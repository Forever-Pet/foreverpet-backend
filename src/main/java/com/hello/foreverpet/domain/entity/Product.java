package com.hello.foreverpet.domain.entity;

import com.hello.foreverpet.auditing.BaseTimeEntity;
import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String productDescription;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Categories categories;
    @NotNull
    private Long productPrice;

    private Long numberOfSold;

    private String productImage;

    // 회원과 찜목록 연관관계 매핑 필요
//    @ManyToOne
//    private Member member;

    //주문과의 연관관계 매핑 필요
//    @ManyToOne
//    private Order order;

    @Builder
    public Product(String productName, String productDescription, Categories categories, Long productPrice,String productImage) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.categories = categories;
        this.productPrice = productPrice;
        this.numberOfSold = 0L;
        this.productImage = productImage;
    }

    public Product updateProductByUpdateRequest(UpdateProductRequest updateProductRequest) {
        this.productName = updateProductRequest.getProductName();
        this.productDescription = updateProductRequest.getProductDescription();
        this.categories = updateProductRequest.getCategories();
        this.productPrice = updateProductRequest.getProductPrice();
        this.productImage = updateProductRequest.getProductImage();

        return this;
    }
}
