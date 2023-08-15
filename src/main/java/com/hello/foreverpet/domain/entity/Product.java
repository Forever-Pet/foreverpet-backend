package com.hello.foreverpet.domain.entity;

import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Wish wish;

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

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }


    // 주문시 판매수량 증가
    public void soldProducts(OrderProduct orderProduct) {
        this.numberOfSold += orderProduct.getOrderProductAmount();
    }

    // 주문 취소시 판매수량 감소
    public void cancelOrder(OrderProduct orderProduct) {
        this.numberOfSold -= orderProduct.getOrderProductAmount();
    }
}