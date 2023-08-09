package com.hello.foreverpet.oauth;

import com.hello.foreverpet.domain.dto.OAuthProvider;

public interface OAuthInfoResponse {

    // AccessToken 요청한 외부 API 프로필 응답값을 우리 서비스의 Model로 변환시키기 위한 인터페이스

    // 카카오 유저 이메일
    String getEmail();

    // 카카오 닉네임
    String getNickname();

    // 소셜 타입 요청용
    OAuthProvider getOAuthProvider();

    Long getId();
}