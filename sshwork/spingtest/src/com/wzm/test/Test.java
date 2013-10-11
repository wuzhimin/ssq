package com.wzm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wzm.invoker.HttpinvokeInterface;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String path = Test.class.getResource("/").getPath()+"com/wzm/test/";
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path + "httpinvoker-client.xml");

		HttpinvokeInterface service = (HttpinvokeInterface) ctx.getBean("httpinvokeService"); // 通过接口得到服务器信息
		int result = service.getHello(); // 调用服务器端的方法
		System.out.println(result);
		System.out.println("远程调用，测试成功!");
	}

}