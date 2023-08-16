package com.hello.foreverpet.service;

import com.hello.foreverpet.config.SecurityUtil;
import com.hello.foreverpet.domain.dto.AuthorityList;
import com.hello.foreverpet.domain.dto.MemberShip;
import com.hello.foreverpet.domain.dto.request.*;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.dto.response.UserDataResponse;
import com.hello.foreverpet.domain.dto.response.UserLoginResponse;
import com.hello.foreverpet.domain.dto.response.UserPasswordResponse;
import com.hello.foreverpet.domain.entity.Authority;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.jwt.JwtFilter;
import com.hello.foreverpet.jwt.TokenProvider;
import com.hello.foreverpet.jwt.JwtTokenProvider;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserInfoJpaRepository userInfoJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long userSignup(UserSignupRequest request) {

        // 가입되어 있지 않은 회원이면,
        // 권한 정보 만들고
        Authority authority = Authority.builder()
                .authorityName(String.valueOf(AuthorityList.ROLE_USER))
                .build();

        // 유저 정보를 만들어서 save
        UserInfo user = UserInfo.builder()
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .userNickname(request.getUserNickName())
                .userPhone(request.getUserPhone())
                .userPoint(0)
                .userDeleteFlag(false)
                .userAddress(request.getUserAddress())
                .authorities(Collections.singleton(authority))
                .userMembership(String.valueOf(MemberShip.SILVER))
                .build();

        return userInfoJpaRepository.save(user).getUserId();
    }


    public UserLoginResponse userLogin(UserLoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword());

        Optional<UserInfo> userData = userInfoJpaRepository.findOneWithAuthoritiesByUserEmail(request.getUserEmail());

        if (userData.get().getUserDeleteFlag()) {
            return new UserLoginResponse("", userData.get().getUserEmail(), userData.get().getUserNickname(), null,
                    false);
        }

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication, String.valueOf(userData.get().getUserId()));

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new UserLoginResponse(jwt, userData.get().getUserEmail(), userData.get().getUserNickname(), httpHeaders,
                true);
    }

    public UserLoginResponse kakaoLogin(Long userId) {

        Optional<UserInfo> userData = userInfoJpaRepository.findById(userId);

        if (userData.get().getUserDeleteFlag()) {
            return new UserLoginResponse("", userData.get().getUserEmail(), userData.get().getUserNickname(), null,
                    false);
        }

        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createKakaoToken(String.valueOf(userData.get().getUserId()));

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new UserLoginResponse(jwt, userData.get().getUserEmail(), userData.get().getUserNickname(), httpHeaders,
                true);
    }

    public UserDataResponse getUserData(Long userId) {

        UserDataResponse user = new UserDataResponse(userInfoJpaRepository.findGetUserData(userId));
        return user;
    }

    @Transactional
    public UserPasswordResponse userNewPassword(String token, UserNewPasswordRequest request) {

        Optional<UserInfo> user = userInfoJpaRepository.findById(
                Long.valueOf(tokenProvider.getAuthentication(token).getName()));

        if (passwordEncoder.matches(request.getUserOriginPassword(), user.get().getUserPassword())) {
            user
                    .map(userInfo -> {
                        return userInfo.updatePassword(passwordEncoder.encode(request.getUserNewPassword()));
                    })
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저데이터 입니다."));
        } else {
            return new UserPasswordResponse("패스워드가 일치하지 않습니다", true);
        }
        return new UserPasswordResponse("패스워드가 변경 성공", true);
    }

    @Transactional
    public Boolean userAddressChange(String token, UserAddressRequest request) {

        Optional<UserInfo> user = userInfoJpaRepository.findById(
                Long.valueOf(tokenProvider.getAuthentication(token).getName()));

        user.map(userInfo -> {
            return userInfo.updateAddress(request.getUserAddress());
        });

        return true;
    }

    @Transactional(readOnly = true)
    public Optional<UserInfo> getUserWithAuthorities(String username) {
        return userInfoJpaRepository.findOneWithAuthoritiesByUserEmail(username);
    }

    @Transactional
    public UserInfo updateUserInfo(Long id, UserUpdateRequest request) {

        return userInfoJpaRepository.findById(id)
                .map(userInfo -> {
                    return userInfo.updateUserData(request);
                })
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저데이터 입니다."));
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<UserInfo> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userInfoJpaRepository::findOneWithAuthoritiesByUserEmail);
    }

    public boolean emailCheck(UserEmailCheckRequest request) {

        Optional<UserInfo> user = userInfoJpaRepository.findByUserEmailAndUserDeleteFlag(request.getUserEmail(), false);

        // true의 경우 사용가능, false의 경우 이메일이 사용불가능
        return user.isEmpty();
    }

    @Transactional
    public boolean addProductInCart(HttpServletRequest httpServletRequest, Long id) {

        String token = httpServletRequest.getHeader("Authorization");
        String userId = jwtTokenProvider.extractSubject(token);

        try {
            UserInfo userInfoByJWTToken = userInfoJpaRepository.findById(Long.valueOf(userId))
                    .orElseThrow(IllegalArgumentException::new);
            Product productById = productJpaRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            userInfoByJWTToken.addProductInCart(productById);

        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    @Transactional
    public boolean addProductInWish(HttpServletRequest httpServletRequest, Long id) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        if (userInfo.isPresent()) {

            log.info("userInfo.get().getUserEmail() = {}",userInfo.get().getUserEmail());

            Product productById = productJpaRepository.findById(id).orElseThrow(IllegalArgumentException::new);

            log.info("productById = {}",productById);

            userInfo.get().addProductInWish(productById);

            log.info(userInfo.get().getCart().getProducts().toString());

            return true;
        }

        return false;
    }


    @Transactional
    public UserInfo userQuit(Long userId) {

        return userInfoJpaRepository.findById(userId)
                .map(UserInfo::quitUser)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저데이터 입니다."));
    }

    public List<ProductResponse> getCart(HttpServletRequest httpServletRequest) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        List<ProductResponse> productResponses = new ArrayList<>();

        if (userInfo.isPresent()) {
            if (userInfo.get().getCart() != null) {
                List<Product> products = userInfo.get().getCart().getProducts();

                if (products != null) {
                    productResponses = products.stream()
                            .map(ProductResponse::new)
                            .collect(Collectors.toList());
                }
            }
        }
        return productResponses;
    }

    public List<ProductResponse> getWish(HttpServletRequest httpServletRequest) {
        Optional<UserInfo> userInfo = getUserInfo(httpServletRequest);

        List<ProductResponse> productResponses = new ArrayList<>();

        if (userInfo.isPresent()) {
            if (userInfo.get().getWish() != null) {
                List<Product> products = userInfo.get().getWish().getProducts();

                if (products != null) {
                    productResponses = products.stream()
                            .map(ProductResponse::new)
                            .collect(Collectors.toList());
                }
            }
        }
        return productResponses;
    }

    private Optional<UserInfo> getUserInfo(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        return userInfoJpaRepository.findById(
                Long.valueOf(tokenProvider.getAuthentication(token).getName()));
    }

}

// Cart ,Wish 의 기능이 생각보다 많다.
// 따로 Service 등을 분리하도록 리팩토링 예정.

// 회원가입 -> 로그인 -> 장바구니 , 찜목록 조회 -> 빈배열 확인
// 회원가입 -> 로그인 -> 장바구니 , 찜목록에 상품 추가 -> 장바구니 or 찜목록 조회 -> 추가한 상품 확인

// 회의때 체크할 내용
// 장바구니 , 찜목록 모두에 한가지 상품이 들어갈수 있는가 ?
// 주문시 장바구니 or 찜목록 에 추가되어 있는 상품은 삭제가 되는가 ? (유저 편의성)