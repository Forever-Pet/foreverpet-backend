package com.hello.foreverpet.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // 회원가입
    SIGNUP_ERROR(500,"S001","회원가입 오류 발생"),
    SIGNUP_EMAIL_ERROR(500,"S002","회원가입 이메일 중복 오류 발생"),

    // 로그인
    LOGIN_EMAIL_ERROR(500,"L001","로그인 이메일 정보 오류 발생"),
    LOGIN_PASSWORD_ERROR(500,"L002","로그인 패스워드 정보 오류 발생"),

    // GET
    USER_ID_ERROR(500,"G001","토큰에서 유저 ID를 가져오지 못하는 오류 발생"),

    // 기타 에러
    ALL_ERROR(500,"A001","알 수 없는 에러 발생"),
    TOKEN_ERROR(500,"A002","헤더에 토큰 인증 에러 발생"),

    // user
    USER_NOT_FOUND(500,"U001","유저 정보를 찾을수 없습니다."),

    // product
    PRODUCT_FOUND_ERROR(500,"P001","상품 정보를 찾을수 없습니다."),
    ALREADY_EXIST_PRODUCT_EXCEPTION(500,"E001","이미 존재하는 상품입니다."),

    // order
    ORDER_FOUND_ERROR(500,"Or001","주문 정보를 찾을수 없습니다."),

    // category
    CATEGORY_NOT_FOUND(500, "c001", "카테고리 정보를 찾을수 없습니다."),
    CATEGORY_ALREADY_EXIST(500, "c002", "이미 존재하는 카테고리 입니다."),
    ;

    private final int status;
    private final String errorCodeNumber;
    private final String message;
}