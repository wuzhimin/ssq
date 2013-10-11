package com.fhway.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class TestAfterAdvice implements AfterReturningAdvice{

    public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
        // TODO Auto-generated method stub
        System.out.println("Thank you,Come again! ");
    }
}
