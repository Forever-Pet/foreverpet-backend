package com.hello.foreverpet.controller;

import com.hello.foreverpet.domain.dto.request.*;
import com.hello.foreverpet.domain.dto.response.*;
import com.hello.foreverpet.domain.entity.UserInfo;
import com.hello.foreverpet.jwt.TokenProvider;
import com.hello.foreverpet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Login API",description = "User Login API 입니다")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    @CrossOrigin(origins="*")
    @Operation(summary = "일반 회원가입",description = "회원가입 성공시 true, 실패시 false.")
    @PostMapping("/user/signup")
    public ResponseEntity<Boolean> signup(@RequestBody UserSignupRequest userSignupRequest) {

        Long userId = userService.userSignup(userSignupRequest);

        Boolean signCheck = false;

        if(userId > 0){
            signCheck = true;
        }

        // true false 로 처리;
        return ResponseEntity.ok(signCheck);
    }

    @Operation(summary = "로그인",description = "로그인 진행 / deleteFlag = false면 탈퇴, true면 로그인 가능")
    @PostMapping("/user/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {

        UserLoginResponse userLoginResponse = userService.userLogin(userLoginRequest);

        return new ResponseEntity<>(userLoginResponse, userLoginResponse.getHttpHeaders(), HttpStatus.OK);
    }

    @Operation(summary = "이메일 중복 확인", description = "true의 경우 이메일 사용가능, false의 경우 이메일 사용불가")
    @PostMapping("/user/emailCheck")
    public ResponseEntity<Boolean> emailCheck(@RequestBody UserEmailCheckRequest userEmailCheckRequest){

        // true의 경우 사용가능, false의 경우 이메일이 사용불가능
        Boolean userEmailCheck = userService.emailCheck(userEmailCheckRequest);

        return new ResponseEntity<>(userEmailCheck, HttpStatus.OK);
    }

    @Operation(summary = "유저 정보 수정", description = "유저 정보 수정 기능")
    @PutMapping("/user")
    public ResponseEntity<UserInfo> modifyUserData(@RequestHeader HttpHeaders header, @Valid @RequestBody UserUpdateRequest userUpdateRequest){

        // null 처리 필요
        String token = ((header.get("Authorization").toString()).substring(7, header.get("Authorization").toString().length()-1)).trim();

        userService.updateUserInfo(Long.valueOf(tokenProvider.getAuthentication(token).getName()), userUpdateRequest);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Operation(summary = "유저 정보 조회", description = "유저 정보 조회 기능")
    @GetMapping("/user")
    public ResponseEntity<UserDataResponse> getUserData(@RequestHeader HttpHeaders header){

        // null 처리 필요
        String token = ((header.get("Authorization").toString()).substring(7, header.get("Authorization").toString().length()-1)).trim();

        UserDataResponse user = userService.getUserData(Long.valueOf(tokenProvider.getAuthentication(token).getName()));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "유저 탈퇴", description = "유저 탈퇴 기능")
    @PutMapping("/user/quit")
    public ResponseEntity<UserQuitResponse> quitUser(@RequestHeader HttpHeaders header){

        // null 처리 필요
        String token = ((header.get("Authorization").toString()).substring(7, header.get("Authorization").toString().length()-1)).trim();

        Boolean flag = userService.userQuit(Long.valueOf(tokenProvider.getAuthentication(token).getName())).getUserDeleteFlag();

        String msg = "";
        if(flag){
            msg = "탈퇴 완료";
        }else{
            msg = "탈퇴 실패";
        }

        UserQuitResponse response = new UserQuitResponse(msg, flag);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "유저 비밀번호 변경 1차 체크", description = "유저 비밀번호 변경 1차 체크 (기존 비밀번호) - 일치 시 true, 불일치 시 false")
    @PostMapping("/user/passwordCheck")
    public ResponseEntity<Boolean> userPasswordCheck(@RequestHeader HttpHeaders header, @Valid @RequestBody UserPasswordRequest request){

        // null 처리 필요
        String token = ((header.get("Authorization").toString()).substring(7, header.get("Authorization").toString().length()-1)).trim();

        return new ResponseEntity<>(userService.userPasswordCheck(token, request), HttpStatus.OK);
    }

    @Operation(summary = "유저 비밀번호 변경", description = "유저 비밀번호 변경")
    @PostMapping("/user/password")
    public ResponseEntity<UserPasswordResponse> userNewPassword(@RequestHeader HttpHeaders header, @Valid @RequestBody UserNewPasswordRequest request){

        // null 처리 필요
        String token = ((header.get("Authorization").toString()).substring(7, header.get("Authorization").toString().length()-1)).trim();

        return new ResponseEntity<>(userService.userNewPassword(token, request), HttpStatus.OK);
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

    @Operation(summary = "로그인 유저의 장바구니 조회", description = "로그인한 유저의 장바구니 상품 리스트")
    @PostMapping("/user/cart")
    public ResponseEntity<List<ProductResponse>> getLoginUserCart(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(userService.getCart(httpServletRequest));
    }

    @Operation(summary = "로그인 유저의 찜목록 조회", description = "로그인한 유저의 찜목록 상품 리스트")
    @PostMapping("/user/wish")
    public ResponseEntity<List<ProductResponse>> getLoginUserWish(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(userService.getWish(httpServletRequest));
    }
}