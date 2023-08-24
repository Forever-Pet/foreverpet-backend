package com.hello.foreverpet.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "유저 정보DTO")
public class UserPasswordResponse {

    private String msg;

    private Boolean result;

    public UserPasswordResponse(String msg, Boolean result) {
        this.msg = msg;
        this.result = result;
    }
}
