/*
 * DeployPage.java
 *
 * Created on __DATE__, __TIME__
 */

package setup.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.*;

import setup.tomcat.TomcatLinuxLauncher;
import setup.tomcat.TomcatWindowsLauncher;
import util.OS;

/**
 * 9. 启动 Tomcat 向导页
 */
public class LaunchTomcatPage extends TextAreaButtonWizardPage {
	public static String getDescription() {
		return "启动 Tomcat";
	}
	

	public LaunchTomcatPage() {
		setButtonText("启动 Tomcat");
		enableSkip();
	}
	
	protected void renderingPage() {
		Map settings = getWizardDataMap();

		setText("现在项目已经成功发布, 可以立即启动 Tomcat :" + getWizardData("tomcat.home") + 
				", 本功能目前只支持 Windows 和 Linux, 并且 Tomcat服务器配置正确.");
	}

	@Override
	public void buttonClick() {
		
		setBusy(true);

		// 检查数据库连接
		appendLine("正在准备启动 Tomcat 服务器...");

		String tomcatPath = (String) getWizardData("tomcat.home");
		if(tomcatPath != null) {
			TomcatWindowsLauncher launcher = new TomcatWindowsLauncher();
			
			if(OS.isLinux()) {
				launcher = new TomcatLinuxLauncher();
			}
			
				try {
					launcher.setAppServerHome(tomcatPath);
					launcher.startServer();
					
					appendLine("Tomcat服务器启动成功:" + tomcatPath );
					setProblem(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					appendLine("Tomcat 启动失败, 您可能需要手工启动此Tomcat.");
					setProblem("Tomcat 启动失败, 您可能需要手工启动此Tomcat.");
				}
				
		}
		setBusy(false);
	}

}