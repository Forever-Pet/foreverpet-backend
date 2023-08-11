package com.hello.foreverpet.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "유저 패스워드 요청DTO")
public class UserNewPasswordRequest {

    private String userPassword;

    private String userPasswordCheck;
}
