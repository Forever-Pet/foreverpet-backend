package com.hello.foreverpet.controller;

import com.hello.foreverpet.domain.dto.request.*;
import com.hello.foreverpet.domain.dto.response.*;
import com.hello.foreverpet.jwt.TokenProvider;
import com.hello.foreverpet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Login API",description = "User Login API 입니다")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

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
    public ResponseEntity<UserModifyResponse> modifyUserData(@RequestHeader HttpHeaders header, @Valid @RequestBody UserUpdateRequest userUpdateRequest){

        // null 처리 필요
        String token = ((header.get("Authorization").toString()).substring(7, header.get("Authorization").toString().length()-1)).trim();
        userService.updateUserInfo(Long.valueOf(tokenProvider.getAuthentication(token).getName()), userUpdateRequest);

        return new ResponseEntity<>(new UserModifyResponse("유저 정보 수정 완료", true), HttpStatus.OK);
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

    @Operation(summary = "유저 비밀번호 변경", description = "유저 비밀번호 변경")
    @PostMapping("/user/password")
    public ResponseEntity<UserPasswordResponse> userPasswordChange(@RequestHeader HttpHeaders header, @Valid @RequestBody UserNewPasswordRequest request){

        // null 처리 필요
        String token = ((header.get("Authorization").toString()).substring(7, header.get("Authorization").toString().length()-1)).trim();

        return new ResponseEntity<>(userService.userNewPassword(token, request), HttpStatus.OK);
    }

    @Operation(summary = "유저 배송지 변경", description = "유저 배송지 변경 / 변경 성공 시 true ")
    @PostMapping("/user/address")
    public ResponseEntity<Boolean> userAddressChange(@RequestHeader HttpHeaders header, @Valid @RequestBody UserAddressRequest request){

        // null 처리 필요
        String token = ((header.get("Authorization").toString()).substring(7, header.get("Authorization").toString().length()-1)).trim();

        return new ResponseEntity<>(userService.userAddressChange(token, request), HttpStatus.OK);
    }
}