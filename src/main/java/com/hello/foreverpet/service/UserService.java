package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
import com.hello.foreverpet.domain.entity.Product;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.jwt.JwtTokenProvider;
import com.hello.foreverpet.repository.ProductJpaRepository;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoJpaRepository userInfoJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public Long userSignup(UserSignupRequest request){

        UserInfo userInfo = request.toEntity();
        Long userNo = userInfoJpaRepository.save(userInfo).getUserNo();

        return userNo;
    }

    public boolean addProductInCart(String token,Long id){

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

    public boolean addProductInWish(String token,Long id){

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
