package com.hello.foreverpet.controller;

import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
import com.hello.foreverpet.service.ProductService;
import com.hello.foreverpet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Login API",description = "User Login API 입니다")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 상품 등록
    @Operation(summary = "일반 회원가입",description = "일반 회원가입 진행.")
    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {

        Long userNo = userService.userSignup(userSignupRequest);

        return ResponseEntity.ok(userNo);
    }
}
