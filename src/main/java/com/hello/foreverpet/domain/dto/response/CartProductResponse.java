package com.hello.foreverpet.domain.dto.response;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.entity.CartProduct;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "장바구니 상품 응답")
public class CartProductResponse {
    private Long id;
    private Long productId;
    private String productName;
    private String productDescription;
    private Categories categories;
    private Long productPrice;
    private Long numberOfSold;
    private String productImage;
    private String brandName;
    private Long quantity;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public CartProductResponse(CartProduct cartProduct) {
        this.id = cartProduct.getId();
        this.productName = cartProduct.getProduct().getProductName();
        this.productDescription = cartProduct.getProduct().getProductDescription();
        this.categories = cartProduct.getProduct().getCategories();
        this.productPrice = cartProduct.getProduct().getProductPrice();
        this.numberOfSold = cartProduct.getProduct().getNumberOfSold();
        this.productImage = cartProduct.getProduct().getProductImage();
        this.brandName = cartProduct.getProduct().getBrandName();
        this.quantity = cartProduct.getQuantity();
        this.createdDate = cartProduct.getProduct().getCreateDate();
        this.modifiedDate = cartProduct.getProduct().getModifiedDate();
    }

    @QueryProjection
    public CartProductResponse(
            Long id,
            Long productId,
            String productName,
            String productDescription,
            Categories categories,
            Long productPrice,
            Long numberOfSold,
            String productImage,
            String brandName,
            Long quantity,
            LocalDateTime createdDate,
            LocalDateTime modifiedDate
    ) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.categories = categories;
        this.productPrice = productPrice;
        this.numberOfSold = numberOfSold;
        this.productImage = productImage;
        this.brandName = brandName;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
