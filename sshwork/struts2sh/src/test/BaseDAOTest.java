package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.*;



public class BaseDAOTest {

	public static void main(String[] args) {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");

		BaseDAO dao = (BaseDAO)ctx.getBean("BaseDAO");
		System.out.println( dao.deleteById("Resource", "14") );
		
	}

}