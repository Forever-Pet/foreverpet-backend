package com.hello.foreverpet.handler;

import lombok.Getter;

@Getter
public class RuntimeExceptionHandler extends RuntimeException {

    private final ErrorCode errorCode;

    public RuntimeExceptionHandler(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
