package com.hello.foreverpet.domain.dto.request;

import com.hello.foreverpet.domain.dto.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "유저 배송지 요청DTO")
public class UserAddressRequest {

    private Address userAddress;

}
