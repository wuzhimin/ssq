package com.wzm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wzm.server.service.ssq.SsqRecordService;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String path = Test.class.getResource("/").getPath();
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path + "httpinvoker-client.xml");

		SsqRecordService service = (SsqRecordService) ctx.getBean("ssqRecordService"); // 通过接口得到服务器信息
//		service.writeAllSsqBaseStats(); // 调用服务器端的方法
		System.out.println("远程调用，测试成功!");
	}

}