package com.hello.foreverpet.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RuntimeExceptionHandler extends RuntimeException {

    private final ErrorCode errorCode;
}
