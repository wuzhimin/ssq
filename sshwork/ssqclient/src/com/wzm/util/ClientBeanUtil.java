package com.wzm.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.service.base.BaseService;
import com.wzm.test.Test;

public class ClientBeanUtil {
	
	private static ApplicationContext ctx = null;
	
	private synchronized static ApplicationContext getApplicationContext() {
		if(ctx == null) {
			String path = Test.class.getResource("/").getPath();
			ctx = new FileSystemXmlApplicationContext(path + "httpinvoker-client.xml");
		}
		
		return ctx;
	}
	
	/**
	 * 根据服务名称获取服务实例
	 * @param serviceName
	 * @return
	 */
	public static BaseService getService(String serviceName) {
		ApplicationContext ctx = getApplicationContext();
		BaseService service = (BaseService)ctx.getBean(serviceName);
		return service;
	}
	
	/**
	 * 根据dao名称获取dao实例
	 * @param daoName
	 * @return
	 */
	public static BaseDao getDao(String daoName) {
		ApplicationContext ctx = getApplicationContext();
		BaseDao dao = (BaseDao)ctx.getBean(daoName);
		return dao;
	}
}
