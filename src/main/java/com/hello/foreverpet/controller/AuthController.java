package com.hello.foreverpet.controller;

import com.hello.foreverpet.domain.dto.OAuthProvider;
import com.hello.foreverpet.domain.dto.response.UserLoginResponse;
import com.hello.foreverpet.oauth.kakao.KakaoLoginParams;
import com.hello.foreverpet.service.OAuthLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Kakao Login API",description = "Kakao Login API 입니다")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @Operation(summary = "소셜 로그인",description = "소셜 로그인(카카오) 진행 / deleteFlag = false면 탈퇴, true면 로그인 가능")
    @PostMapping("/user/kakao")
    public ResponseEntity<UserLoginResponse> loginKakao(@RequestBody KakaoLoginParams params) {

        /* param에는 프론트에서 넘겨준 카카오 회원의 인가코드가 저장되어있다 */
        // https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=a6bfe93b460dd365f9951854df478ab9&redirect_uri=http://localhost:5173/oauth/kakaoCallback&response_type=code

        UserLoginResponse kakaoResponse = oAuthLoginService.login(params);
        kakaoResponse.setUserSocialType(String.valueOf(OAuthProvider.KAKAO));

        return new ResponseEntity<>(kakaoResponse, kakaoResponse.getHttpHeaders(), HttpStatus.OK);
    }
}