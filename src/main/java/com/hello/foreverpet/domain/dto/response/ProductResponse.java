package com.hello.foreverpet.domain.dto.response;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(description = "상품 응답")
public class ProductResponse {
    private Long id;
    private String productName;
    private String productDescription;
    private Categories categories;
    private Long productPrice;
    private Long numberOfSold;
    private String productImage;
    private String brandName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ProductResponse(Product product) {
        this.id = product.getProductId();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.categories = product.getCategories();
        this.productPrice = product.getProductPrice();
        this.numberOfSold = product.getNumberOfSold();
        this.productImage = product.getProductImage();
        this.brandName = product.getBrandName();
        this.createdDate = product.getCreateDate();
        this.modifiedDate = product.getModifiedDate();
    }


}