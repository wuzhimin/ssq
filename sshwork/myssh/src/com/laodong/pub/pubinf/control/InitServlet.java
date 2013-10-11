package com.laodong.pub.pubinf.control;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class InitServlet extends HttpServlet {
	private static BeanFactory factory;
    //开启daemon服务的定时器。
    public void init() throws ServletException
    {
        try
        {
        	initBeanFactory();
        	System.out.println("Load BeanFactory");
//            DaemonService.getInstance();
            System.out.println("Load DaemonServlet");
        }
        catch(Exception e)
        {
            System.out.println("DaemonServlet.init() error:" + e.getMessage());
            e.printStackTrace();
        }
    }
    public ServletContext getServletContext() {
    	// TODO Auto-generated method stub
    	return super.getServletContext();
    }
    private void initBeanFactory(){
    	ClassPathResource resource = new ClassPathResource("applicationContext.xml");
		factory = new XmlBeanFactory(resource);
    }
    public static BeanFactory getBeanFactory(){
    	return factory;
    }
}
