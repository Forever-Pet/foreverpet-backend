package com.hello.foreverpet.domain.entity;

import com.hello.foreverpet.auditing.BaseTimeEntity;
import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.Categories;
import com.hello.foreverpet.domain.dto.OAuthProvider;
import com.hello.foreverpet.domain.dto.response.UserLoginResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user_info")
@Getter
@NoArgsConstructor
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

    // 상복님 장바구니 , 찜목록 관련 이쪽에 작업하겠습니다.
    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> cart = new ArrayList<>();

    private OAuthProvider oAuthProvider;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> wish = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public UserInfo(String userNickname, String userEmail, String userPassword,
                    String userPhone, Address userAddress,
                Boolean userDeleteFlag, Integer userPoint, OAuthProvider oAuthProvider,
                    Set<Authority> authorities, String username, String password){
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userDeleteFlag = userDeleteFlag;
        this.userPoint = userPoint;
        this.oAuthProvider = oAuthProvider;
        this.authorities = authorities;
    }

    public void addProductInCart(Product product) {
        this.cart.add(product);
    }

    public void addProductInWish(Product product) {
        this.wish.add(product);
    }

}
