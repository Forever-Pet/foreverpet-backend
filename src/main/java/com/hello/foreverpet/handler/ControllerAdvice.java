package com.hello.foreverpet.handler;

import com.hello.foreverpet.domain.dto.response.ErrorResponse;
import com.hello.foreverpet.domain.exception.user.ProductNotFoundException;
import com.hello.foreverpet.domain.exception.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerEmailDuplicateException(UserNotFoundException ex) {

        ErrorResponse response = null;

        if (ex.getErrorCode().getErrorCodeNumber().equals("S001")) {
            response = new ErrorResponse(ErrorCode.SIGNUP_ERROR);
        } else if (ex.getErrorCode().getErrorCodeNumber().equals("S002")) {
            response = new ErrorResponse(ErrorCode.SIGNUP_EMAIL_ERROR);
        } else if (ex.getErrorCode().getErrorCodeNumber().equals("L001")) {
            response = new ErrorResponse(ErrorCode.LOGIN_EMAIL_ERROR);
        } else if (ex.getErrorCode().getErrorCodeNumber().equals("L002")) {
            response = new ErrorResponse(ErrorCode.LOGIN_PASSWORD_ERROR);
        } else if (ex.getErrorCode().getErrorCodeNumber().equals("G001")) {
            response = new ErrorResponse(ErrorCode.USER_ID_ERROR);
        } else {
            response = new ErrorResponse(ErrorCode.ALL_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundExceptionHandler(ProductNotFoundException e) {
        ErrorResponse response = new ErrorResponse(ErrorCode.PRODUCT_FOUND_ERROR);

        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getErrorCode().getStatus()));
    }
}
