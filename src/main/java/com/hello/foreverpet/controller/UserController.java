package com.hello.foreverpet.controller;

import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.service.ProductService;
import com.hello.foreverpet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Login API",description = "User Login API 입니다")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "일반 회원가입",description = "일반 회원가입 진행.")
    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody @Valid UserSignupRequest userSignupRequest) {

        Long userNo = userService.userSignup(userSignupRequest);

        return ResponseEntity.ok(userNo);
    }

    @Operation(summary = "일반 회원가입",description = "일반 회원가입 진행.")
    @GetMapping("/login")
    public ResponseEntity<UserInfo> login(@RequestBody @Valid UserSignupRequest userSignupRequest) {

        UserInfo user = new UserInfo();

        return ResponseEntity.ok(null);
    }
}
