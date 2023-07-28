package com.hello.foreverpet.domain.dto.request;

import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "상품 등록 요청DTO")
public class NewProductRequest {
    @NotNull
    private String productName;
    @NotNull
    private String productDescription;
    @NotNull
    private String categories;
    @NotNull
    private Long productPrice;
    @NotNull
    private String productImage;
    @NotNull
    private String brandName;

    public Product toEntity() {
        return Product.builder().productName(this.productName)
                .productDescription(this.productDescription)
                .categories(Categories.valueOf(this.categories))
                .productPrice(this.productPrice)
                .productImage(this.productImage)
                .brandName(this.brandName)
                .build();
    }

    @Builder
    public NewProductRequest(String productName, String productDescription, String categories, Long productPrice,
                             String productImage,String brandName) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.categories = categories;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.brandName = brandName;
    }
}
