package com.hello.foreverpet.domain.dto.request;

import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.dto.OAuthProvider;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "회원가입 요청DTO")
public class UserSignupRequest {

    private String userNickName;

    private String userEmail;

    private String userPassword;

    private String userPhone;

    private Address userAddress;


    public UserInfo toEntity() {
        return UserInfo.builder()
                .userNickname(this.userNickName)
                .userEmail(this.userEmail)
                .userPassword(this.userPassword)
                .userPhone(this.userPhone)
                .userAddress(this.userAddress)
                .build();
    }

    @Builder
    public UserSignupRequest(String userNickName, String userEmail, String userPassword, String userPhone, Address userAddress){
        this.userNickName = userNickName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

}
