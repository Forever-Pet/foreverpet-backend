package com.hello.foreverpet.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "상품 수정 DTO")
@Builder
public class UpdateProductRequest {
    @Schema(description = "변경할 상품 이름",example = "수정된상품")
    @NotNull
    private String productName;
    @Schema(description = "변경할 상품 설명",example = "수정된설명")
    @NotNull
    private String productDescription;
    @Schema(description = "변경할 상품 카테고리",example = "SNACK")
    @NotNull
    private String categories;
    @Schema(description = "변경할 상품 가격",example = "99000")
    @NotNull
    private Long productPrice;
    @Schema(description = "변경할 상품 이미지",example = "http://foreverpet/image11")
    @NotNull
    private String productImage;
    @Schema(description = "변경할 상품 브랜드",example = "MS")
    @NotNull
    private String brandName;


}
