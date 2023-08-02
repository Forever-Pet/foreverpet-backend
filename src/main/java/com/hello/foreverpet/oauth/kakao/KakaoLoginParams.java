package com.hello.foreverpet.oauth.kakao;

import com.hello.foreverpet.domain.dto.OAuthProvider;
import com.hello.foreverpet.oauth.OAuthLoginParams;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {

    private String authorizationCode;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {

        /* 카카오 API 요청을 위한 인가코드 : authorizationCode */

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }
}