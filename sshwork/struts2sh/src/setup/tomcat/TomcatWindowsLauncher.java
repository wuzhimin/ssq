/*
 * @(#)TomcatWindowsLauncher.java 1.00 2006-12-17
 *
 * Copyright 2006 BeanSoft Studio. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package setup.tomcat;

import java.io.File;


/**
 * TomcatWindowsLauncher
 * 
 * Chinese documents:
 * 
 * @author BeanSoft
 * @version 1.00 2006-12-17
 */
public class TomcatWindowsLauncher{

	private String appServerHome;
	
	/* (non-Javadoc)
	 * @see servermon.launcher.IAppServerLauncher#startServer()
	 */
	public void startServer() throws Exception {
		// 启动 Tomcat 服务器
		try {
			// "{Tomcat_Home}/bin"
			String tomcatRootPath = "\"" + getAppServerHome() + File.separator + "bin\"";
			
			String msg = "正在启动 " + getAppServerName() + " 服务器...\n";
			log(msg);

			Runtime.getRuntime().exec("cmd /c start /D" + tomcatRootPath + " " + getStartupScript());
					
			msg = getAppServerName() + " 服务器启动脚本已执行...\n";
			log(msg);
				
		} catch (Exception e) {
			String msg = "无法启动 " + getAppServerName() + " 服务器:" + e + "\n";
			log(msg);
			throw e;
		}
	}
	
	protected String getStartupScript() {
		// TODO Auto-generated method stub
		return "startup.bat";
	}

	protected String getAppServerName() {
		// TODO Auto-generated method stub
		return "Tomcat";
	}

	public static void log(String msg) {
		System.out.println(msg);
	}

	/* (non-Javadoc)
	 * @see servermon.launcher.IAppServerLauncher#stopServer()
	 */
	public void stopServer() throws Exception {
		// "{Tomcat_Home}/bin"
		String tomcatRootPath = "\"" + getAppServerHome() + File.separator + "bin\"";
				
		// 停止 Tomcat 并等待 30 秒钟期待执行结束
		try {
			Runtime.getRuntime().exec("cmd /c start /D" + tomcatRootPath + " " + getStopScript());
					
			String msg = "等待 " + getAppServerName() + " 服务器关闭, " + getShutDownWaitTime() +" 秒钟后将启动 Tomcat 服务器.\n";
					
			log(msg);
					
		} catch (Exception e) {
			String msg = "无法关闭 " + getAppServerName() + " 服务器:" + e;
			log(msg + "\n");
			throw e;
		}
	}

	protected String getStopScript() {
		// TODO Auto-generated method stub
		return "shutdown.bat";
	}

	protected int getShutDownWaitTime() {
		// TODO Auto-generated method stub
		return 60;
	}

	/**
	 * @return the appServerHome
	 */
	public String getAppServerHome() {
		return appServerHome;
	}

	/**
	 * @param appServerHome the appServerHome to set
	 */
	public void setAppServerHome(String appServerHome) {
		this.appServerHome = appServerHome;
	}

}
