/**
 * 
 */
package setup.swing;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardPage;

/**
 * 服务器配置运行向导主类.
 * 
 * @author BeanSoft
 * 
 */
public class SetupWizard {

	/**
	 * 创建只显示文本的向导页面.
	 * @param stepName - 步骤名
	 * @param text - 显示的多行文本
	 * @return WizardPage 对象
	 */
	static WizardPage createTextPage(String stepName, String text) {
		WizardPage page = new WizardPage(stepName);
		JTextArea textArea = new JTextArea(text);
		// textArea.setFocusable(false);
		textArea.setEditable(false);
		textArea.setBackground(page.getBackground());

		page.setLayout(new BorderLayout());
		page.add(new JScrollPane(textArea));

		return page;
	}

	/**
	 * 从资源文件创建只显示文本的向导页面.
	 * @param stepName - 步骤名
	 * @param resFile - 显示的多行文本资源文件
	 * @return WizardPage 对象
	 */
	static WizardPage createTextPageFromResFile(String stepName, String resFile) {
		try {
			return createTextPage(stepName, util.FileUtil
					.readFileAsString(SetupWizard.class
							.getResourceAsStream(resFile)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return createTextPage(stepName, "错误: 无法加载资源文件 " + resFile);
		}
	}

	public static void main(String[] args) {
		System.setProperty("wizard.sidebar.image",
				"org/netbeans/modules/wizard/bg_small.png");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 显示任务栏设置和图标
		final Frame f = new Frame("struts2sh 项目配置向导");
		f.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(
				SetupWizard.class.getResource("network.png")));

		f.pack();
		f.setLocation(100, 100);
		f.setVisible(true);

		Wizard wiz = WizardPage.createWizard("struts2sh 项目配置向导", new WizardPage[] {
				// Both of these classes are subclasses of
				// WizardPage. We could also pass instances
				// of WizardPage.
				createTextPageFromResFile("配置说明", "summary.txt"),
				createTextPageFromResFile("检查先决条件", "req.txt"),
				new ConfigDBPage(), 
				new CheckDBConnectionPage(),
				new CreateDatabasePage(),
				new CreateDBTablePage(),
				new SaveHibernateCfgPage(),
				new DeployPage(),
				new LaunchTomcatPage(),
				createTextPageFromResFile("完成", "finish.txt") });


		Map gatheredSettings = (Map) WizardDisplayer.showWizard(wiz,
				new java.awt.Rectangle(100, 100, 700, 500), new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						try {
							JOptionPane
									.showMessageDialog(
											f,
											util.FileUtil
											.readFileAsString(SetupWizard.class
													.getResourceAsStream("help.txt"))
									);
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				}, null);
		System.out.println(gatheredSettings);

		System.exit(0);
	}

}
