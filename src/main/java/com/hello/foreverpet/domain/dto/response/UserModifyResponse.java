package com.hello.foreverpet.domain.dto.response;

import com.hello.foreverpet.domain.entity.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Optional;

@Data
@Schema(description = "유저 수정 DTO")
public class UserModifyResponse {

    private String msg;

    private boolean result;

    public UserModifyResponse(String msg, boolean result) {
        this.msg = msg;
        this.result = result;
    }
}
