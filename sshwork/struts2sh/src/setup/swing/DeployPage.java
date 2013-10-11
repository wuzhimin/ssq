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

/**
 * 8. 发布项目 向导页
 */
public class DeployPage extends TextAreaButtonWizardPage {
	public static String getDescription() {
		return "发布项目";
	}
	

	public DeployPage() {
		setButtonText("发布项目");
		
		JPanel p = new JPanel();
		p.add(new JLabel("请输入 Tomcat 的安装路径:"));
		final JTextField tf = new JTextField();
		tf.setColumns(20);
		tf.setName("tomcat.home");
		p.add(tf);
		
		JButton browseButton = new JButton("浏览...");
		browseButton.addActionListener(new ActionListener() {
			JFileChooser chooser = new JFileChooser();
			public void actionPerformed(ActionEvent e) {
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setDialogTitle("请选择 Tomcat 的安装目录");
				if(chooser.showOpenDialog(DeployPage.this) == JFileChooser.APPROVE_OPTION) {
					try {
						tf.setText(chooser.getSelectedFile().getCanonicalPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						tf.setText(chooser.getSelectedFile().getPath());
					}
				}
			}
			
		});
		p.add(browseButton);
		
		add(p, BorderLayout.NORTH);
	}
	
	

	protected String validateContents(Component component, Object event) {
		String tomcatPath = (String) getWizardData("tomcat.home");
		
		if(util.StringUtil.isEmpty(tomcatPath)) {
			return ("Tomcat 安装目录不能为空");

		} else {
			File tomcatDir = new File(tomcatPath);
			if (!(new File(tomcatDir, "webapps")).exists())
			{
				return ("您输入的目录不是有效的Tomcat 安装目录");
			}
		}
		
		return null;
	}


	protected void renderingPage() {
		Map settings = getWizardDataMap();

		setText("现在准备工作已经完成, 可以点击 \"发布项目\"按钮将 WebRoot 目录下的内容作为 struts2sh项目发布到 Tomcat 下");
	}

	@Override
	public void buttonClick() {
		if(validateContents(null, null) != null) {
			return;
		}
		
		setBusy(true);

		// 检查数据库连接
		appendLine("正在准备发布 Web应用...");

		String tomcatPath = (String) getWizardData("tomcat.home");
		if(tomcatPath != null) {
				try {
					util.FileOperate.copyFolder(new File(ConfigParams.WEBROOT).getCanonicalPath(),
							new File(tomcatPath, 
									"webapps/" + ConfigParams.WAR_NAME).getCanonicalPath());
					appendLine("项目" + ConfigParams.WAR_NAME + "成功发布到了如下的Tomcat目录下:" + tomcatPath + ", 您现在可以启动服务器来查看结果.");
					setProblem(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					setProblem("项目发布失败");
				}
				
		}
		setBusy(false);
	}

}