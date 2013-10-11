package com.wzm.test;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wzm.server.entity.User;
import com.wzm.server.service.UserService;
import com.wzm.util.MD5Util;

public class ServiceTest {
	
	public String getPath() {
		return this.getClass().getResource("/").getPath();
	}
	
	public static void  main(String[] args) {
		String path  = new ServiceTest().getPath();
		ApplicationContext ctx = new FileSystemXmlApplicationContext(path+"applicationContext.xml");
		UserService userService = (UserService)ctx.getBean("userService");
		
		// 增加一个用户
		User user = new User();
		user.setPwd(MD5Util.md5("admin"));
		user.setName("admin"+ System.currentTimeMillis());
		
		Serializable id = userService.add(user);
		
		//查找
		user = userService.findUserById((long)23);
		
		if(user==null) {
			user = new User();
			user.setPwd(MD5Util.md5("admin"));
			user.setName("admin"+ System.currentTimeMillis());
		}
		
		// 修改
		user.setPwd(MD5Util.md5("ddd"));
		userService.save(user);
		
		List<User> users = userService.getUserList("from User");
		System.out.println(users);
	}
}
