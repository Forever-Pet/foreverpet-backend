package com.hello.foreverpet.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "로그인 요청DTO")
public class UserEmailCheckRequest {

    private String userEmail;
}
