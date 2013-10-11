/*
 * @(#)TomcatLinuxLauncher.java 1.00 2006-12-17
 *
 * Copyright 2006 BeanSoft Studio. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package setup.tomcat;


/**
 * TomcatLinuxLauncher
 * 
 * Chinese documents:
 * Linux 版本的启动程序.
 * 
 * @author BeanSoft
 * @version 1.00 2006-12-17
 */
public class TomcatLinuxLauncher extends TomcatWindowsLauncher {
	
	/* (non-Javadoc)
	 * @see setup.tomcat.TomcatWindowsLauncher#getStartupScript()
	 */
	@Override
	protected String getStartupScript() {
		// TODO Auto-generated method stub
		return "startup.sh";
	}

	/* (non-Javadoc)
	 * @see setup.tomcat.TomcatWindowsLauncher#getStopScript()
	 */
	@Override
	protected String getStopScript() {
		// TODO Auto-generated method stub
		return "shutdown.sh";
	}

	/* (non-Javadoc)
	 * @see servermon.launcher.IAppServerLauncher#startServer()
	 */
	public void startServer() throws Exception {
		// 启动 Tomcat 服务器
		try {
			// "{Tomcat_Home}/bin"
			String msg = "正在启动 " + getAppServerName() + " 服务器...\n";
			System.out.println(msg);

			Runtime.getRuntime().exec(getAppServerHome() + "/bin/" + getStartupScript());
					
			msg = getAppServerName() + " 服务器启动脚本已执行...\n";
			log(msg);
		} catch (Exception e) {
			String msg = "无法启动 " + getAppServerName() + " 服务器:" + e + "\n";
			log(msg);
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see servermon.launcher.IAppServerLauncher#stopServer()
	 */
	public void stopServer() throws Exception {
		// "{Tomcat_Home}/bin"
				
		// 停止 Tomcat 并等待 30 秒钟期待执行结束
		try {
			Runtime.getRuntime().exec(getAppServerHome() + "/bin/" + getStopScript());
					
			String msg = "等待 " + getAppServerName() + " 服务器关闭, " + getShutDownWaitTime() +" 秒钟后将启动 Tomcat 服务器.\n";
					
			log(msg);
					
		} catch (Exception e) {
			String msg = "无法关闭 " + getAppServerName() + " 服务器:" + e;
			log(msg + "\n");
			throw e;
		}
	}
	
	
}
