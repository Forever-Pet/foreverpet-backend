package com.hello.foreverpet.service;

import com.hello.foreverpet.config.SecurityUtil;
import com.hello.foreverpet.domain.dto.AuthorityList;
import com.hello.foreverpet.domain.dto.request.*;
import com.hello.foreverpet.domain.dto.response.ProductResponse;
import com.hello.foreverpet.domain.dto.response.UserLoginResponse;
import com.hello.foreverpet.domain.entity.Authority;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.jwt.JwtFilter;
import com.hello.foreverpet.jwt.TokenProvider;
import com.hello.foreverpet.jwt.JwtTokenProvider;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserInfoJpaRepository userInfoJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

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
                .build();

        Long userId = userInfoJpaRepository.save(user).getUserId();

        return userId;
    }


    public UserLoginResponse userLogin(UserLoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword());

        Optional<UserInfo> userData = userInfoJpaRepository.findOneWithAuthoritiesByUserEmail(request.getUserEmail());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication, String.valueOf(userData.get().getUserId()));

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new UserLoginResponse(jwt, userData.get().getUserEmail(), userData.get().getUserNickname(), httpHeaders);
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

        Optional<UserInfo> user = userInfoJpaRepository.findByUserEmail(request.getUserEmail());

        // true의 경우 사용가능, false의 경우 이메일이 사용불가능
        return user.isEmpty();
    }

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

    public boolean addProductInWish(HttpServletRequest httpServletRequest, Long id) {
        String token = httpServletRequest.getHeader("Authorization");
        String userId = jwtTokenProvider.extractSubject(token);

        try {
            Product productById = productJpaRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            UserInfo userInfoByJWTToken = userInfoJpaRepository.findById(Long.valueOf(userId))
                    .orElseThrow(IllegalArgumentException::new);

            userInfoByJWTToken.addProductInWish(productById);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<ProductResponse> getCart(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String userId = jwtTokenProvider.extractSubject(token);

        UserInfo userInfo = userInfoJpaRepository.findById(Long.valueOf(userId))
                .orElseThrow(IllegalArgumentException::new);

        return userInfo.getCart().stream().map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getWish(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String userId = jwtTokenProvider.extractSubject(token);

        UserInfo userInfo = userInfoJpaRepository.findById(Long.valueOf(userId))
                .orElseThrow(IllegalArgumentException::new);

        return userInfo.getWish()
                .stream().map(ProductResponse::new)
                .collect(Collectors.toList());
    }
}
