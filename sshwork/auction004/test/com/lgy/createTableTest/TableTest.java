package com.lgy.createTableTest;

import junit.framework.TestCase;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TableTest extends TestCase{
	
	/**
	 * 浣滆�锛氶檰骞挎湁
	 * 鏂规硶璇存槑锛氭祴璇曠敓鎴愯〃
	 * 鍙傛暟璇存槑锛�
	 * 2011-9-23
	 */
	
	public void testTest(){
		Configuration configuration = new AnnotationConfiguration().configure();
		 SchemaExport schemaExport = new SchemaExport(configuration);
		 schemaExport.create(true, true);
		
		/*BeanFactory factory=new ClassPathXmlApplicationContext("beans.xml");
		Users users=(Users)factory.getBean("user");
		System.out.println(users.getUname());*/
		
	}
	

}
