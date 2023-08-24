package com.hello.foreverpet.domain.dto.response;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.entity.CartProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "장바구니 상품 응답")
public class CartProductResponse {
    private Long id;
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
}