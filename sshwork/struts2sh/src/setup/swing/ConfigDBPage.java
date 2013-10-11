/*
 * ConfigDBPage.java
 *
 * Created on __DATE__, __TIME__
 */

package setup.swing;

import java.awt.Component;
import java.sql.Connection;
import java.util.Map;

import javax.swing.UIManager;

import org.netbeans.api.wizard.WizardDisplayer;
import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardPage;
import org.netbeans.spi.wizard.WizardPanelNavResult;

import setup.db.DatabaseManager;
import setup.db.DatabaseManagerImplMysql;
import util.FileUtil;
import util.StringUtil;

/**
 * 3: 数据库连接参数 向导页.
 */
public class ConfigDBPage extends WizardPage implements ConfigParams {

	public static String getDescription() {
		return "数据库连接参数";
	}

	/** Creates new form ConfigDBPage */
	public ConfigDBPage() {
		initComponents();

		try {
			jTextArea1.setText(util.FileUtil.readFileAsString(getClass()
					.getResourceAsStream("dbconfig.txt")));
			jTextArea1.setBackground(getBackground());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证输入值.
	 */
	protected String validateContents(Component component, Object event) {
		Map settings = getWizardDataMap();
	
		String username = (String) (settings.get("jdbc.username"));
		String host = (String) (settings.get("jdbc.host"));
		String port = (String) (settings.get("jdbc.port"));
		String dbName = (String) (settings.get("jdbc.dbname"));
		String pwd = (String) (settings.get("jdbc.password"));

		// 检查有效性
		if (StringUtil.isEmpty(username)) {
			return ("用户名不能为空");
			
		}

		if (StringUtil.isEmpty(host)) {
			return ("主机名不能为空");
			
		}

		if (StringUtil.isEmpty(port)) {
			return ("端口号不能为空");
		}
		
		if (StringUtil.parseInt(port) <= 0 || StringUtil.parseInt(port) > 65535) {
			return ("请输入有效的端口号, 范围为 1 ~ 65535");
		}

		if (StringUtil.isEmpty(dbName)) {
			return ("数据库名不能为空");
		}
		
        return null;
    }
	

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		jTextField5 = new javax.swing.JTextField();

		jLabel1.setText("\u7528\u6237\u540d:");

		jTextField1.setColumns(10);
		jTextField1.setText("root");
		jTextField1.setName("jdbc.username");

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jLabel2.setText("\u5bc6\u7801:");

		jLabel3.setText("\u4e3b\u673a\u540d\u6216\u8005IP:");

		jLabel4.setText("\u7aef\u53e3\u53f7:");

		jLabel5
				.setText("\u6570\u636e\u5e93\u540d(\u4e0d\u5b58\u5728\u5c06\u81ea\u52a8\u521b\u5efa):");

		jTextField2.setColumns(10);
		jTextField2.setName("jdbc.password");

		jTextField3.setColumns(10);
		jTextField3.setText("localhost");
		jTextField3.setName("jdbc.host");

		jTextField4.setColumns(5);
		jTextField4.setText("3306");
		jTextField4.setName("jdbc.port");

		jTextField5.setColumns(5);
		jTextField5.setEditable(false);
		jTextField5.setText("struts2sh");
		jTextField5.setName("jdbc.dbname");

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jScrollPane1,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																395,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				54,
																				54,
																				54)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING)
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												jLabel5)
																										.addPreferredGap(
																												org.jdesktop.layout.LayoutStyle.RELATED)
																										.add(
																												jTextField5,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																												88,
																												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
																						.add(
																								layout
																										.createSequentialGroup()
																										.add(
																												layout
																														.createParallelGroup(
																																org.jdesktop.layout.GroupLayout.LEADING)
																														.add(
																																jLabel1)
																														.add(
																																jLabel2)
																														.add(
																																jLabel3)
																														.add(
																																jLabel4))
																										.add(
																												40,
																												40,
																												40)
																										.add(
																												layout
																														.createParallelGroup(
																																org.jdesktop.layout.GroupLayout.LEADING)
																														.add(
																																jTextField4,
																																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																														.add(
																																jTextField3,
																																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																														.add(
																																layout
																																		.createParallelGroup(
																																				org.jdesktop.layout.GroupLayout.TRAILING,
																																				false)
																																		.add(
																																				org.jdesktop.layout.GroupLayout.LEADING,
																																				jTextField2)
																																		.add(
																																				org.jdesktop.layout.GroupLayout.LEADING,
																																				jTextField1)))))))
										.addContainerGap(173, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.add(
												jScrollPane1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel1)
														.add(
																jTextField1,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel2)
														.add(
																jTextField2,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.UNRELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel3)
														.add(
																jTextField3,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(jLabel4)
														.add(
																jTextField4,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jLabel5)
														.add(
																jTextField5,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(51, Short.MAX_VALUE)));
	}// </editor-fold>
	//GEN-END:initComponents

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	// End of variables declaration//GEN-END:variables
	/* (non-Javadoc)
	 * @see org.netbeans.spi.wizard.WizardPage#allowNext(java.lang.String, java.util.Map, org.netbeans.spi.wizard.Wizard)
	 */

}