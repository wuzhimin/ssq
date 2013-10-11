package test;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.Role;
import cn.beansoft.scm.entity.User;
import cn.beansoft.scm.entity.Vendor;


public class RoleDAOTest {

	public static void main(String[] args) {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");

		RoleDAO dao = (RoleDAO)ctx.getBean("RoleDAO");
		System.out.println(dao.findById(Role.class, 1));
		System.out.println(dao.findByProperty("Role", "roleName", "ÐÂ½ÇÉ«"));
	}

}