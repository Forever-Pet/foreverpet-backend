package com.hello.foreverpet.handler;

import com.hello.foreverpet.domain.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handlerEmailDuplicateException(RuntimeExceptionHandler ex){

        ErrorResponse response = null;

        if(ex.getErrorCode().getErrorCode().equals("S001")){
            response = new ErrorResponse(ErrorCode.SIGNUP_ERROR);
        }else if(ex.getErrorCode().getErrorCode().equals("S002")){
            response = new ErrorResponse(ErrorCode.SIGNUP_EMAIL_ERROR);
        }else if(ex.getErrorCode().getErrorCode().equals("L001")){
            response = new ErrorResponse(ErrorCode.LOGIN_EMAIL_ERROR);
        }else if(ex.getErrorCode().getErrorCode().equals("L002")){
            response = new ErrorResponse(ErrorCode.LOGIN_PASSWORD_ERROR);
        }else if(ex.getErrorCode().getErrorCode().equals("G001")){
            response = new ErrorResponse(ErrorCode.USER_ID_ERROR);
        }else{
            response = new ErrorResponse(ErrorCode.ALL_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }
}
