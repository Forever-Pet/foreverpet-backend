package com.hello.foreverpet.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "user_info")
@Getter
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "user_no")
    private Long userNo;

    @NotNull
    @Column(name = "user_email")
    private String userEmail;

    @NotNull
    @Column(name = "user_pw")
    private String userPw;

    @NotNull
    @Column(name = "user_phone")
    private String userPhone;

    @NotNull
    @Column(name = "user_address")
    private String userAddress;

    @NotNull
    @Column(name = "user_zipcode")
    private String userZipcode;

    @Column(name = "user_social_type")
    private String userSocialType;

    @Column(name = "user_access_token")
    private String userAccessToken;

    @NotNull
    @Column(name = "user_joindate")
    private LocalDate userJoindate;

    @NotNull
    @Column(name = "user_modifydate")
    private LocalDate userModifydate;

    @NotNull
    @Column(name = "user_delete_yn")
    private Boolean userDeleteYn;

    @NotNull
    @Column(name = "user_point")
    private Integer userPoint;

    @NotNull
    @Column(name = "user_profile_image")
    private String userProfileImage;

}
