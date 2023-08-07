package com.hello.foreverpet.controller;

import com.hello.foreverpet.domain.dto.request.NewProductRequest;
import com.hello.foreverpet.domain.dto.request.UserLoginRequest;
import com.hello.foreverpet.domain.dto.request.UserSignupRequest;
import com.hello.foreverpet.domain.dto.response.UserLoginResponse;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.service.ProductService;
import com.hello.foreverpet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Login API",description = "User Login API 입니다")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CrossOrigin(origins="*")
    @Operation(summary = "일반 회원가입",description = "회원가입 성공시 true, 실패시 false.")
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody UserSignupRequest userSignupRequest) {

        Long userNo = userService.userSignup(userSignupRequest);

        Boolean signCheck = false;

        if(userNo > 0){
            signCheck = true;
        }

        // true false 로 처리;
        return ResponseEntity.ok(signCheck);
    }

    @Operation(summary = "로그인",description = "로그인 진행.")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {

         UserLoginResponse userLoginResponse = userService.userLogin(userLoginRequest);

        return new ResponseEntity<>(userLoginResponse, userLoginResponse.getHttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "이메일 중복 확인", description = "true의 경우 이메일 사용가능, false의 경우 이메일 사용불가")
    @PostMapping("/emailCheck")
    public ResponseEntity<Boolean> emailCheck(@RequestBody UserLoginRequest userLoginRequest){

        // true의 경우 사용가능, false의 경우 이메일이 사용불가능
        Boolean userEmailCheck = userService.emailCheck(userLoginRequest);

        return new ResponseEntity<>(userEmailCheck, HttpStatus.OK);
    }

    @Operation(summary = "로그인 테스트용",description = "로그인 테스트 진행.")
    @PostMapping("/loginAdmin")
    public ResponseEntity<String> loginTestAdmin() {

        // ROLE_ADMIN이 아니면 403 에러 배출
        return new ResponseEntity<>("Admin On",HttpStatus.OK);
    }
    @Operation(summary = "로그인 어드민 테스트용",description = "로그인 어드민 테스트 진행.")
    @PostMapping("/loginUser")
    public ResponseEntity<String> loginTestUser() {

        // ROLE_USER 이 아니면 403 에러 배출
        return new ResponseEntity<>("User On",HttpStatus.OK);
    }

    @Operation(summary = "장바구니에 상품 추가", description = "회원의 장바구니에 상품을 추가.")
    @PostMapping("/cart/{id}")
    public ResponseEntity<Boolean> addProductInCart(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        boolean successProductAddCart = userService.addProductInCart(httpServletRequest, id);
        return ResponseEntity.ok(successProductAddCart);
    }

    @Operation(summary = "찜목록에 상품 추가", description = "회원의 찜목록에 상품을 추가.")
    @PostMapping("/wish/{id}")
    public ResponseEntity<Boolean> addProductInWish(HttpServletRequest httpServletRequest, @PathVariable Long id) {
        boolean successProductAddWish = userService.addProductInWish(httpServletRequest, id);
        return ResponseEntity.ok(successProductAddWish);
    }
}
