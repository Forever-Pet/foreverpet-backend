package com.hello.foreverpet.domain.entity;

import com.hello.foreverpet.auditing.BaseTimeEntity;
import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.OAuthProvider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_info")
@Getter
@NoArgsConstructor
public class UserInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull
    @Column(name = "user_no")
    private Long userNo;

    @NotNull
    @Column(name = "user_nickname")
    private String userNickName;

    @NotNull
    @Column(name = "user_email")
    private String userEmail;

//    @NotNull
    @Column(name = "user_pw")
    private String userPw;

//    @NotNull
    @Column(name = "user_phone")
    private String userPhone;

//    @NotNull
    @Embedded
    private Address userAddress;

//    @NotNull
//    @Column(name = "user_social_type")
//    private String userSocialType;

    @Column(name = "user_access_token")
    private String userAccessToken;

    @Column(name = "user_refresh_token")
    private String userRefreshToken;

//    @NotNull
    @Column(name = "user_delete_yn")
    private Boolean userDeleteYn;

//    @NotNull
    @Column(name = "user_point")
    private Integer userPoint;

//    @NotNull
    @Column(name = "user_profile_image")
    private String userProfileImage;

    @OneToMany
    private List<Product> cart = new ArrayList<>();

    private OAuthProvider oAuthProvider;

    @Builder
    public UserInfo(String userNickname, String userEmail, String userPw, String userPhone, Address userAddress,
                    String userAccessToken, String userRefreshToken,
                    Boolean userDeleteYn, Integer userPoint, String userProfileImage, OAuthProvider oAuthProvider){
        this.userNickName = userNickname;
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userAccessToken= userAccessToken;
        this.userRefreshToken = userRefreshToken;
        this.userDeleteYn = userDeleteYn;
        this.userPoint = userPoint;
        this.userProfileImage = userProfileImage;
        this.oAuthProvider = oAuthProvider;
    }

}
