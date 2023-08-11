package com.hello.foreverpet.domain.dto.response;

import com.hello.foreverpet.domain.entity.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Optional;

@Data
@Schema(description = "유저 정보DTO")
public class UserDataResponse {

    private String user_nickname;

    private Integer user_point;

    private Long coupon_cnt;

    private String user_membership;

    public UserDataResponse(String user_nickname, Integer user_point, Long coupon_cnt, String user_membership) {
        this.user_nickname = user_nickname;
        this.user_point = user_point;
        this.coupon_cnt = coupon_cnt;
        this.user_membership = user_membership;
    }

    public UserDataResponse(Optional<UserInfo> user){
        this.user_nickname = user.get().getUserNickname();
        this.user_point = user.get().getUserPoint();
        this.user_membership = user.get().getUserMembership();
        this.coupon_cnt = 0L;
    }
}
