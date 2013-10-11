package com.laodong.pub.product.spring;

import java.lang.reflect.Method;
import java.util.HashMap;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class MothedAdvisor implements MethodInterceptor,
		MethodBeforeAdvice, AfterReturningAdvice {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object rval = invocation.proceed();
		return rval;
	}

	/**
	 * 目标方法调用前拦截
	 */
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		System.out.println("-------classname before: ");
	}

	/**
	 * 目标方法调用后拦截
	 */
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object target) throws Throwable {
		String classname = target.toString();
		classname = classname.substring(0, classname.indexOf("@") + 1);
		
		System.out.println("-------classname: " + classname);	
		
	}
}
