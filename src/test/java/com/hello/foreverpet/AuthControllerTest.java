package com.hello.foreverpet;

import com.hello.foreverpet.controller.AuthController;
import com.hello.foreverpet.controller.ProductController;
import com.hello.foreverpet.domain.dto.Address;
import com.hello.foreverpet.domain.dto.OAuthProvider;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.oauth.OAuthInfoResponse;
import com.hello.foreverpet.oauth.kakao.KakaoInfoResponse;
import com.hello.foreverpet.repository.UserInfoJpaRepository;
import com.hello.foreverpet.service.OAuthLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.OAuth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class AuthControllerTest {

    @Mock
    private OAuthLoginService oAuthLoginService;

    @Mock
    private UserInfoJpaRepository userInfoJpaRepository;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserTest(){

        // 준비
        KakaoInfoResponse kakaoInfoResponse = new KakaoInfoResponse();

        // public 으로 변경해둠 유의 필요함 (기존 private)
        KakaoInfoResponse.KakaoAccount kakaoAccount = new KakaoInfoResponse.KakaoAccount();
        KakaoInfoResponse.KakaoProfile kakaoProfile = new KakaoInfoResponse.KakaoProfile();

        kakaoAccount.setEmail("email@naver.com");
        kakaoProfile.setNickname("nickName");
        kakaoAccount.setProfile(kakaoProfile);

        when(oAuthLoginService.newMember(kakaoInfoResponse)).thenReturn(1L);

        // 실행
        Long newMemberTests = oAuthLoginService.newMember(kakaoInfoResponse);

        Boolean memberTestTF = true;

        if(newMemberTests < 1L) memberTestTF = false;

        // 단언
        assertEquals(true, memberTestTF);

        verify(oAuthLoginService);
    }

    @Test
    void test(){
        int test;
    }
}
