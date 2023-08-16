package com.hello.foreverpet.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        final Map<String, Object> body = new HashMap<>();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // 응답 객체 초기화
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        if(request.getRequestURI().equals("/user/login")){
            body.put("message", "로그인 패스워드 정보 오류 발생");
            body.put("code", "L002");
        }else{
            body.put("message", "헤더에 토큰 인증 에러 발생");
            body.put("code", "A002");
        }
        final ObjectMapper mapper = new ObjectMapper();
        // response 객체에 응답 객체를 넣어줌
        mapper.writeValue(response.getOutputStream(), body);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}