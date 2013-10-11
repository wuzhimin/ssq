package com;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class MothedAdvisor extends DefaultService implements MethodInterceptor,
		MethodBeforeAdvice, AfterReturningAdvice {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object rval = invocation.proceed();
		System.out.println("--------logDyKey: ");
		return rval;
	}

	/**
	 * 目标方法调用前拦截
	 */
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		System.out.println("--------logDyKey: ");
	}

	/**
	 * 目标方法调用后拦截
	 */
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object target) throws Throwable {
		String classname = target.toString();
		classname = classname.substring(0, classname.indexOf("@") + 1);
		// 拦截方法标识字串：Service路径@方法名称
		String logDyKey = classname + arg1.getName();
		// 根据拦截方法标识字串到日志定义表中匹配对应记录dyObj
		
		System.out.println("--------logDyKey: " + logDyKey);
	}
}
