package com.wzm.util;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.wzm.server.service.base.BaseService;
import com.wzm.server.service.base.UserService;

public class BeanUtil {
	
	/**
	 * 获取spring上下文环境，用于获取bean实例
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		ServletContext sctx = ServletActionContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
		return ctx;
	}
	
	/**
	 * 获取seesion
	 * @return
	 */
	 
	public static Map<String, Object> getSession() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		return session;
	}
	
	/**
	 * 获取用户服务（UserService）实例
	 * @return
	 */
	public static UserService getUserService() {
		ServletContext sctx = ServletActionContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
		UserService userService = (UserService)ctx.getBean("userService");
		return userService;
	}
	
	/**
	 * 根据服务名称获取服务实例
	 * @param serviceName
	 * @return
	 */
	public static BaseService getService(String serviceName) {
		ServletContext sctx = ServletActionContext.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sctx);
		BaseService service = (BaseService)ctx.getBean(serviceName);
		return service;
	}
}
