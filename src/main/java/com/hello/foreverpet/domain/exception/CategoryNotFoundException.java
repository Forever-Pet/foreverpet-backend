package com.hello.foreverpet.domain.exception;

import com.hello.foreverpet.handler.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CategoryNotFoundException extends IllegalArgumentException{
    private final ErrorCode errorCode;
}
