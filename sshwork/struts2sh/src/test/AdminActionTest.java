package test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.beansoft.scm.action.AdminAction;
import cn.beansoft.scm.biz.UserManager;
import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.User;

public class AdminActionTest {

	public static void main(String[] args) {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");

		AdminAction adminAction = (AdminAction)ctx.getBean("AdminAction");

		System.out.println(adminAction.getBaseDAO().queryForCount("select count(id) from User")
				);
	}

}