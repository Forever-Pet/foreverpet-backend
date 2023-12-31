package com.hello.foreverpet.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;

@Data
@Schema(description = "로그인 요청DTO")
public class UserLoginResponse {

    private String accessToken;

    private String userEmail;

    private String userNickname;

    private String userSocialType;

    private HttpHeaders httpHeaders;

    private Boolean deleteFlag;

    public UserLoginResponse(String accessToken, String userEmail, String userNickname, HttpHeaders httpHeaders, Boolean deleteFlag) {
        this.accessToken = accessToken;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.httpHeaders = httpHeaders;
        this.deleteFlag = deleteFlag;
    }
}
