package com.hello.foreverpet.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "상품 수정 DTO")
@Builder
public class UpdateProductRequest {
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


}
