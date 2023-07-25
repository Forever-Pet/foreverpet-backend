package com.hello.foreverpet.service;

import com.hello.foreverpet.domain.dto.OAuthProvider;
import com.hello.foreverpet.oauth.OAuthApiClient;
import com.hello.foreverpet.oauth.OAuthInfoResponse;
import com.hello.foreverpet.oauth.OAuthLoginParams;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        // params.oAuthProvider() : 카카오가 들어감
        // 즉, client는 KakaoApiClient 호출을 의미하는것으로 보임
        OAuthApiClient client = clients.get(params.oAuthProvider());
        /* 인가 코드를 사용하여 KAKAO API에 AccessToken을 받아옴*/
        String accessToken = client.requestAccessToken(params);
        /* 해당 AccessToken을 KAKAO API에 전송하여 해당 로그인 유저의 데이터를 가져옴(email, nickname)*/
        return client.requestOauthInfo(accessToken);
    }
}