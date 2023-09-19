package com.hello.foreverpet.domain.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import com.hello.foreverpet.auditing.BaseTimeEntity;
import com.hello.foreverpet.domain.dto.request.UpdateProductRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(length = 500)
    private String productDescription;

//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private Categories categories;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @NotNull
    private Long productPrice;

    private Long numberOfSold;

    private String productImage;
    @NotNull
    private String brandName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Wish wish;

//    @Builder
//    public Product(String productName, String productDescription, Categories categories, Long productPrice,
//                   String productImage, String brandName) {
//        this.productName = productName;
//        this.productDescription = productDescription;
//        this.categories = categories;
//        this.productPrice = productPrice;
//        this.numberOfSold = 0L;
//        this.productImage = productImage;
//        this.brandName = brandName;
//    }

    @Builder
    public Product(String productName, String productDescription, Category category, Long productPrice,
                   String productImage, String brandName) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.productPrice = productPrice;
        this.numberOfSold = 0L;
        this.productImage = productImage;
        this.brandName = brandName;
    }

    public Product updateProductByUpdateRequest(UpdateProductRequest updateProductRequest) {
        this.productName = updateProductRequest.getProductName();
        this.productDescription = updateProductRequest.getProductDescription();
        this.category = new Category();
        this.productPrice = updateProductRequest.getProductPrice();
        this.productImage = updateProductRequest.getProductImage();
        this.brandName = updateProductRequest.getBrandName();
        return this;
    }


    public void setWish(Wish wish) {
        this.wish = wish;
        if (wish != null && !wish.getProducts().contains(this)) {
            wish.getProducts().add(this);
        }

    }

    // 주문시 판매수량 증가
    public void soldProducts() {
        this.numberOfSold++;
    }

    // 주문 취소시 판매수량 감소
    public void cancelOrder() {
        if (numberOfSold > 1) {
            this.numberOfSold--;
        }

    }
}