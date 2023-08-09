package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.oauth.AuthTokens;
import com.hello.foreverpet.oauth.AuthTokensGenerator;
import com.hello.foreverpet.oauth.OAuthInfoResponse;
import com.hello.foreverpet.oauth.OAuthLoginParams;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final UserInfoJpaRepository userInfoJpaRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        /* params 에는 code(인가코드) 만 저장 되어 있음*/
        // oAuthInfoResponse에 유저 정보가 담겨있음
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        /* 회원가입이 되어 있지 않으면 DB에 유저 정보를 저장, 저장되어 있지 않으면 검색해서 데이터를 불러옴*/
        Long memberId = findOrCreateMember(oAuthInfoResponse);

        /* 해당 Id를 기준으로 JWT AccessToken, RefreshToken을 생성 */
        /*
            "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdW~~",
            "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdW~~",
            "grantType": "Bearer",
            "expiresIn": 1800
        */
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return userInfoJpaRepository.findByUserNickname(oAuthInfoResponse.getNickname())
                .map(UserInfo::getUserId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    public Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        UserInfo userInfo = UserInfo.builder()
                .userNickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return userInfoJpaRepository.save(userInfo).getUserId();
    }
}