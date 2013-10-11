package com.fhway.spring.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.laodong.framework.MyDispatchAction;

public class TestBeforeAdvice implements MethodInterceptor,
MethodBeforeAdvice, AfterReturningAdvice {
    public void before(Method method,Object[] args,Object target) throws Throwable{
    	String userid = (String)MyDispatchAction.get();
    	System.out.println("------当前用户名: " + userid);
    	System.out.println("--------------拦截类实例: " + target.getClass().getName());
    	System.out.println("--------------拦截方法名: " + method.getName());
        System.out.println("--------------拦截方法参数个数: " + args.length);    
   }
    public Object invoke(MethodInvocation invocation) throws Throwable {
		Object rval = invocation.proceed();
//		System.out.println("--------logDyKey invoke: " + invocation.getArguments());
		return rval;
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
		
//		System.out.println("--------logDyKey afterReturning: " + logDyKey);
	}
}
