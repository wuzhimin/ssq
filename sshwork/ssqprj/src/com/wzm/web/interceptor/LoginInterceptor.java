package com.wzm.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.wzm.util.BeanUtil;

public class LoginInterceptor implements Interceptor  {

	private static final long serialVersionUID = -10273122124720920L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		Map<String, Object> session = BeanUtil.getSession();
		
		// 未登录跳转首页
		if(session==null || !session.containsKey("username")) {
			return "index";
		}
		
		return arg0.invoke();
	}

}
