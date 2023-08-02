package com.hello.foreverpet.controller;

import com.hello.foreverpet.oauth.AuthTokens;
import com.hello.foreverpet.oauth.kakao.KakaoLoginParams;
import com.hello.foreverpet.service.OAuthLoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    @Operation(summary = "소셜 로그인",description = "소셜 로그인(카카오) 진행.")
    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        /* param에는 프론트에서 넘겨준 카카오 회원의 인가코드가 저장되어있다 */
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}