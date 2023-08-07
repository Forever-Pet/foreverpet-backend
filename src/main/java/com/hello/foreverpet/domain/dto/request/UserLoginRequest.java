package com.hello.foreverpet.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "로그인 요청DTO")
public class UserLoginRequest {

    private String userEmail;

    private String userPassword;
}
