package com.hello.foreverpet.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "상품 수정 DTO")
public class UpdateProductRequest {
    private String productName;
    private String productDescription;
    private String categories;
    private Long productPrice;
    private String productImage;
    private String brandName;
}
