/**
 * 
 */
package cn.beansoft.scm.listeners;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.beansoft.scm.dao.BaseDAO;
import cn.beansoft.scm.dao.ResourceDAO;
import cn.beansoft.scm.entity.AppConfig;

/**
 * 应用程序初始化的监听器, 将把AppConfig对象放入Application中.
 * @author BeanSoft
 *
 */
public class AppInitListener implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext application = event.getServletContext();
		// 获取 Spring 的 Bean 工厂
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		BaseDAO dao = (BaseDAO) ctx.getBean("BaseDAO");
		AppConfig appConfig = (AppConfig) dao.findById(AppConfig.class, new Integer(1));
		System.out.println("初始化全局配置:" + appConfig.getAppTitle());
		application.setAttribute("appConfig", appConfig);
		// 分析并保存敏感词列表
		String words = appConfig.getBadwords();
		String[] wordsArray = words.split("\r\n");
		ArrayList<String> wordLists = new ArrayList<String>();
		for (String word : wordsArray) {
			System.out.println("处理敏感词 " + word);
			wordLists.add(word);
		}
		
		util.BadWordFilter.setWordList(wordLists);
		
	}

}
