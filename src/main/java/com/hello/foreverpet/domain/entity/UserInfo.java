package com.hello.foreverpet.domain.entity;

import com.hello.foreverpet.auditing.BaseTimeEntity;
import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.OAuthProvider;
import com.hello.foreverpet.domain.dto.request.UserUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "user_info")
@Getter
@NoArgsConstructor
@Slf4j
public class UserInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

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

    @Column(name = "user_delete_flag")
    private Boolean userDeleteFlag;

    @Column(name = "user_point")
    private Integer userPoint;

    @Column(name = "user_kakao_id")
    private Long userKakaoId;

    @Column(name = "user_membership")
    private String userMembership;


    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToOne(mappedBy = "userInfo",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Wish wish;

    private OAuthProvider oAuthProvider;

    private Long coupon_cnt;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public UserInfo(String userNickname, String userEmail, String userPassword,
                    String userPhone, Address userAddress,
                    Boolean userDeleteFlag, Integer userPoint, OAuthProvider oAuthProvider, String userSocialType,
                    Set<Authority> authorities, Long userKakaoId, String userMembership) {
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userDeleteFlag = userDeleteFlag;
        this.userPoint = userPoint;
        this.oAuthProvider = oAuthProvider;
        this.authorities = authorities;
        this.userSocialType = userSocialType;
        this.userKakaoId = userKakaoId;
        this.userMembership = userMembership;
    }

    public UserInfo updateUserData(UserUpdateRequest userSignupRequest) {
        this.userNickname = userSignupRequest.getUserNickName();
        this.userPhone = userSignupRequest.getUserPhone();
        this.userAddress = userSignupRequest.getUserAddress();
        return this;
    }

    public UserInfo quitUser() {
        this.userDeleteFlag = true;
        return this;
    }

    public UserInfo updatePassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public UserInfo updateAddress(Address address) {
        this.userAddress = address;
        return this;
    }

//    public void addProductInCart(Product product) {
//        if (cart == null) {
//            cart = new Cart();
//        }
//        cart.setUserInfo(this);
//        cart.addProductInCart(product);
//    }


    public void addProductInCart(CartProduct cartProduct) {
        if (cart == null) {
            cart = new Cart();
        }
        cart.setUserInfo(this);
        cart.addProductInCart(cartProduct);
    }

    public void addProductInWish(Product product) {
        if (wish == null) {
            wish = new Wish();
        }
        wish.setUserInfo(this);
        wish.addProductInWish(product);
    }


}