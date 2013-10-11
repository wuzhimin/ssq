package setup.swing;

import java.sql.Connection;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.*;

import setup.db.DatabaseManager;
import setup.db.DatabaseManagerImplMysql;

/**
 * 6. 初始化数据库表 向导页
 * 
 */
public class CreateDBTablePage extends TextAreaButtonWizardPage {
	public static String getDescription() {
		return "初始化数据库表";
	}

	public CreateDBTablePage() {
		setButtonText("创建表");
		enableSkip();
	}

	protected void renderingPage() {
		Map settings = getWizardDataMap();

		setText("本页将创建必须的数据库表, 加载的配置文件是 setup/mysql.sql\n");
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
		appendLine("正在连接数据库...");

		String url = "jdbc:mysql://" + host + ":" + port + "/";
		DatabaseManager dbman = new DatabaseManagerImplMysql();
		// 创建数据库
		try {
			Connection conn = dbman.checkConnection(ConfigParams.JDBC_DRIVER,
					url + ConfigParams.MYSQL_ENCODING, username, pwd);
			dbman.setConnectioin(conn);

			dbman.changeDatabase(dbName);

			appendLine("正在创建表格...");
			// Every sql statement is sperated by a ;
			StringTokenizer token = new StringTokenizer(util.FileUtil
					.readFileAsString("setup/mysql.sql", "UTF-8"), ";");
			boolean tableSuccess = true;// Whether all table has been created

			while (token.hasMoreElements()) {
				String value = token.nextElement().toString();
				System.out.println(value);
				tableSuccess &= dbman.executeUpdate(value);
			}

			if (tableSuccess) {
				appendLine("表格创建完毕.");
				setProblem(null);
			} else {
				appendLine("无法创建表格! 请检查后您的数据库服务器设置.");
				setProblem("无法创建表格! 请检查后您的数据库服务器设置.");
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
