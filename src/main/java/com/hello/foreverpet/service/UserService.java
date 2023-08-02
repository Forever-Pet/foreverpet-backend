package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoJpaRepository userInfoJpaRepository;

    public Long userSignup(UserSignupRequest request){

        UserInfo userInfo = request.toEntity();
        Long userNo = userInfoJpaRepository.save(userInfo).getUserNo();

        return userNo;
    }

}
