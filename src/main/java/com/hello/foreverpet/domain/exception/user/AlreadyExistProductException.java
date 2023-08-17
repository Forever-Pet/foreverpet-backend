package com.hello.foreverpet.domain.exception.user;

import com.hello.foreverpet.handler.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AlreadyExistProductException extends IllegalArgumentException{

    private final ErrorCode errorCode;
}
