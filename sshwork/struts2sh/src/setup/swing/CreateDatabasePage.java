/**
 * 
 */
package setup.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.netbeans.spi.wizard.Wizard;
import org.netbeans.spi.wizard.WizardPanelNavResult;

import setup.db.DatabaseManager;
import setup.db.DatabaseManagerImplMysql;
import util.FileUtil;
import util.StringUtil;

/**
 * 5. 创建数据库 向导页
 * 
 */
public class CreateDatabasePage extends TextAreaButtonWizardPage {
	public static String getDescription() {
		return "创建数据库";
	}
	
	public CreateDatabasePage() {
		setButtonText("开始创建");
		enableSkip();
	}
	
	protected void renderingPage() {
		Map settings = getWizardDataMap();

		setText("本页将检查并创建数据库" + settings.get("jdbc.dbname"));
	}

	@Override
	public void buttonClick() {
		setBusy(true);

		Map settings = getWizardDataMap();
		String username = (String) (settings.get("jdbc.username"));
		String host = (String) (settings.get("jdbc.host"));
		String port = (String) (settings.get("jdbc.port"));
		String dbName = (String) (settings.get("jdbc.dbname"));
		String pwd = (String) (settings.get("jdbc.password"));

		// 检查数据库连接
		setText("");
		appendLine("正在检查数据库 " + dbName + "是否存在...");

		String url = "jdbc:mysql://" + host + ":" + port + "/";
		
		DatabaseManager dbman = new DatabaseManagerImplMysql();
		// 创建数据库
		try {
			Connection conn = dbman.checkConnection(ConfigParams.JDBC_DRIVER, url, username,
					pwd);
			dbman.setConnectioin(conn);

			if (dbman.checkDatabaseExist(dbName)) {
				appendLine("数据库" + dbName + "已存在,无须重复创建!");
				setProblem(null);
			} else {
				appendLine("数据库" + dbName + "不存在,正在创建...");
				
				if (dbman.createDatabase(dbName)) {
					appendLine("数据库"
									+ dbName + "创建成功!");
					setProblem(null);
				} else {
					appendLine("无法创建数据库"
									+ dbName + "! 请检查后您的数据库服务器设置.");
					setProblem("无法创建数据库"
							+ dbName + "! 请检查后您的数据库服务器设置.");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbman.closeConnection();
		}
		setBusy(false);		
	}

}
