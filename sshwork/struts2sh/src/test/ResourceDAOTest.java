package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.beansoft.scm.dao.*;
import cn.beansoft.scm.entity.*;



public class ResourceDAOTest {

	public static void main(String[] args) {
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("applicationContext.xml");

		ResourceDAO dao = (ResourceDAO)ctx.getBean("ResourceDAO");
		System.out.println(dao.findById(Resource.class, 2));
		Resource r = new Resource();
		r.setUri("/abc/*");
		r.setScmRole((Role) dao.findById(Role.class, 1));
		r.setAddDate(new java.util.Date());
		
		dao.save(r);
		
	}

}