package test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.beansoft.scm.biz.UserManager;
import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.User;

public class UserTest {

	public static void main(String[] args) {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");

		UserDAO dao = (UserDAO)ctx.getBean("UserDAO");
		UserManager manager = (UserManager)ctx.getBean("UserManager");
		
		
		User user  = new User();
//		user.setName("beansoft");
//		user.setGender(0);
//		user.setRealName("刘长炯");
//		user.setPassword("123456");
//		user.setAddress("中国北京海淀");
//		user.setPostCode("100000");
//		user.setBirthday(new java.util.Date());
//		user.setUserType(0);
//		user.setRegDate(new java.util.Date());
//		
//		
//		cn.beansoft.scm.dao.save(user);
		
		user = dao.findByName("beansoft").get(0);
		
		System.out.println(user.getName());
		System.out.println(user.isActive());
		
		try {
			System.out.println(manager.checkLogin("admin", "123456", 4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}