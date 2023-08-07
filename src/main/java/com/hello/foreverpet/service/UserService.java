package com.hello.foreverpet.service;

import com.hello.foreverpet.config.SecurityUtil;
import com.hello.foreverpet.domain.dto.request.UserLoginRequest;
import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
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

    public Long userSignup(UserSignupRequest request){

        // 가입되어 있지 않은 회원이면,
        // 권한 정보 만들고
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        // 유저 정보를 만들어서 save
        UserInfo user = UserInfo.builder()
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .userNickname(request.getUserNickName())
                .userPhone(request.getUserPhone())
                .userPoint(0)
                .userDeleteYn(false)
                .userAddress(request.getUserAddress())
                .authorities(Collections.singleton(authority))
                .build();

        Long userNo = userInfoJpaRepository.save(user).getUserNo();

        return userNo;
    }

    public UserLoginResponse userLogin(UserLoginRequest request){

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword());

        Optional<UserInfo> userData = userInfoJpaRepository.findOneWithAuthoritiesByUserEmail(request.getUserEmail());

        // authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication, String.valueOf(userData.get().getUserNo()));

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new UserLoginResponse(jwt, userData.get().getUserEmail(), userData.get().getUserNickname(), httpHeaders);
    }

    @Transactional(readOnly = true)
    public Optional<UserInfo> getUserWithAuthorities(String username) {
        return userInfoJpaRepository.findOneWithAuthoritiesByUserEmail(username);
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<UserInfo> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userInfoJpaRepository::findOneWithAuthoritiesByUserEmail);
    }

    public boolean emailCheck(UserLoginRequest request){

        Optional<UserInfo> user = userInfoJpaRepository.findByUserEmail(request.getUserEmail());

        // true의 경우 사용가능, false의 경우 이메일이 사용불가능
        return user.isEmpty();
    }
    public boolean addProductInCart(HttpServletRequest httpServletRequest,Long id){
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

    public boolean addProductInWish(HttpServletRequest httpServletRequest,Long id){
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

}
