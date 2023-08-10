package com.hello.foreverpet.domain.dto.request;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.entity.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "회원수정 요청DTO")
public class UserUpdateRequest {
    private String userNickName;

    private String userPhone;

    private Address userAddress;

    public UserInfo toEntity() {
        return UserInfo.builder()
                .userNickname(this.userNickName)
                .userPhone(this.userPhone)
                .userAddress(this.userAddress)
                .build();
    }

    @Builder
    public UserUpdateRequest(String userNickName, String userEmail, String userPassword, String userPhone, Address userAddress){
        this.userNickName = userNickName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

}
