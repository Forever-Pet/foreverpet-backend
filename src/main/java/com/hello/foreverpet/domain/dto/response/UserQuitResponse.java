package com.hello.foreverpet.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
@Schema(description = "탈퇴 요청DTO")
public class UserQuitResponse {

      private String msg;

      private Boolean deleteFlag;

    public UserQuitResponse(String msg, Boolean deleteFlag) {
        this.msg = msg;
        this.deleteFlag = deleteFlag;
    }
}
