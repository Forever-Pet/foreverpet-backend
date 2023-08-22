package com.hello.foreverpet.utils.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* hello.foreverpet.service.*.*(..))")
    public void allService() {
    }

}
