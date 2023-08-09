package com.hello.foreverpet.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderProductListRequest {
    
    @NotNull
    private Long orderProductId;                        // 상품번호

    @NotNull
    private Integer orderProductAmount;                    // 상품수량

}
