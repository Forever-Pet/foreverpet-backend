package com.hello.foreverpet.domain.dto.request;

import com.hello.foreverpet.domain.entity.Category;
import com.hello.foreverpet.domain.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "상품 등록 요청DTO")
public class NewProductRequest {
    @Schema(description = "등록할 상품 이름",example = "상품1")
    @NotNull
    private String productName;
    @Schema(description = "등록할 상품 설명",example = "이 상품을 구매하세요")
    @NotNull
    private String productDescription;
    @Schema(description = "등록할 상품 카테고리",defaultValue = "SNACK",allowableValues = {"SNACK","BITA","FOOD"})
    @NotNull
    private String categories;
    @Schema(description = "등록할 상품 가격",example = "5000")
    @NotNull
    private Long productPrice;
    @Schema(description = "등록할 상품 이미지 주소",example = "http://forevepet/image/1")
    @NotNull
    private String productImage;
    @Schema(description = "등록할 상품 브랜드",example = "애플")
    @NotNull
    private String brandName;

    public Product toEntity() {
        return Product.builder().productName(this.productName)
                .productDescription(this.productDescription)
                .category(this.categories)
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
