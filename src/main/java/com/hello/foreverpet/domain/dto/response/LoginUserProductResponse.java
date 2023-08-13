package com.hello.foreverpet.domain.dto.response;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.entity.Product;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LoginUserProductResponse {
    private Long id;
    private String productName;
    private String productDescription;
    private Categories categories;
    private Long productPrice;
    private Long numberOfSold;
    private String productImage;
    private String brandName;
    private boolean inCart;
    private boolean inWish;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public LoginUserProductResponse(Product product) {
        this.id = product.getProductId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.categories = product.getCategories();
        this.productPrice = product.getProductPrice();
        this.numberOfSold = product.getNumberOfSold();
        this.productImage = product.getProductImage();
        this.brandName = product.getBrandName();
        this.inCart = false;
        this.inWish = false;
        this.createdDate = product.getCreateDate();
        this.modifiedDate = product.getModifiedDate();
    }

    public void reverseInCart() {
        this.inCart = true;
    }

    public void reverseInWish() {
        this.inWish = true;
    }
}
