package setup.swing;

import java.sql.Connection;
import java.util.Map;

import setup.db.DatabaseManager;
import setup.db.DatabaseManagerImplMysql;

/**
 * 4. 测试数据库连接向导页
 * 
 */
public class CheckDBConnectionPage extends TextAreaButtonWizardPage {
	public static String getDescription() {
		return "测试数据库连接";
	}

	public CheckDBConnectionPage() {
		setButtonText("测试连接");
		enableSkip();
	}
	
	protected void renderingPage() {
		Map settings = getWizardDataMap();

		setText("本页将检查MySQL 数据库连接是否可用");
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
		setText("正在连接数据库...");

		String url = "jdbc:mysql://" + host + ":" + port + "/";
		DatabaseManager dbman = new DatabaseManagerImplMysql();
		try {
			Connection conn = dbman.checkConnection(ConfigParams.JDBC_DRIVER,
					url, username, pwd);
			conn.close();
			appendLine("数据库连接成功!");

			setProblem(null);
		} catch (Exception e) {
			// e.printStackTrace();
			appendLine("数据库连接失败!");
			appendLine(e.getMessage());

			setProblem("数据库连接失败, 请检查参数!");
		}

		setBusy(false);

	}

}
