package com.hello.foreverpet.oauth;

import com.hello.foreverpet.domain.dto.OAuthProvider;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {

    // OAuth 요청을 위한 파라미터 값을 가지고 있는 인터페이스

    OAuthProvider oAuthProvider();
    MultiValueMap<String, String> makeBody();
}