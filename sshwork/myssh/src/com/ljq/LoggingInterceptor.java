package com.ljq;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LoggingInterceptor implements MethodBeforeAdvice {
	   public void before(Method method, Object[] objects, Object o) throws Throwable {
	        System.out.println("logging before!");
	    }
	}
