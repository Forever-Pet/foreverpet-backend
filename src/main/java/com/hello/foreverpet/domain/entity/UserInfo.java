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
    private String userNickname;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_pw")
    private String userPassword;

    @Column(name = "user_phone")
    private String userPhone;

    @Embedded
    private Address userAddress;

    @Column(name = "user_social_type")
    private String userSocialType;

//    @Column(name = "user_access_token")
//    private String userAccessToken;
//
//    @Column(name = "user_refresh_token")
//    private String userRefreshToken;

    @Column(name = "user_delete_yn")
    private Boolean userDeleteYn;

    @Column(name = "user_point")
    private Integer userPoint;

//    @Column(name = "user_profile_image")
//    private String userProfileImage;


    // 상복님 장바구니 , 찜목록 관련 이쪽에 작업하겠습니다.
    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> cart = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> wish = new ArrayList<>();

    private OAuthProvider oAuthProvider;

    @Builder
    public UserInfo(String userNickname, String userEmail, String userPassword, String userPhone, Address userAddress,
                    String userAccessToken, String userRefreshToken,
                    Boolean userDeleteYn, Integer userPoint, String userProfileImage, OAuthProvider oAuthProvider) {
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
//        this.userAccessToken= userAccessToken;
//        this.userRefreshToken = userRefreshToken;
        this.userDeleteYn = userDeleteYn;
        this.userPoint = userPoint;
//        this.userProfileImage = userProfileImage;
        this.oAuthProvider = oAuthProvider;
    }

    public void addProductInCart(Product product) {
        this.cart.add(product);
    }

    public void addProductInWish(Product product) {
        this.wish.add(product);
    }

}
