package setup.swing;

import java.sql.Connection;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.*;

import org.netbeans.spi.wizard.WizardPanelNavResult;

import setup.db.DatabaseManager;
import setup.db.DatabaseManagerImplMysql;
import util.FileUtil;

/**
 * 7. 保存 Hibernate 连接参数 向导页
 * 
 */
public class SaveHibernateCfgPage extends TextAreaButtonWizardPage {
	public static String getDescription() {
		return "保存 Hibernate 连接参数";
	}

	public SaveHibernateCfgPage() {
		setButtonText("保存Hibernate配置");
		enableSkip();
	}

	protected void renderingPage() {
		Map settings = getWizardDataMap();

		setText("本页将把数据库连接信息写入 WEB-INF/classes 下面的 Hibernate 全局配置文件"
				+ ConfigParams.HIBERNATE_TEMPLATE_XML);
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
		// 保存数据库连接参数
		try {
			String template = util.FileUtil.readFileAsString(getClass()
					.getResourceAsStream(ConfigParams.HIBERNATE_TEMPLATE_FILE));

			template = template.replaceAll("_username_", username);
			template = template.replaceAll("_password_", pwd);
			template = template.replaceAll("_url_", url + dbName);

			appendLine("配置文件详细信息:" + template);

			if (!FileUtil.writeFileString(ConfigParams.WEB_INF_PATH_CLASSES
					+ "/" + ConfigParams.HIBERNATE_TEMPLATE_XML, template,
					"UTF-8")) {
				setProblem("无法写入 Hibernate 配置文件信息 "
						+ ConfigParams.HIBERNATE_TEMPLATE_FILE);

			} else {
				appendLine("成功保存 Hibernate 配置文件信息:"
						+ ConfigParams.WEB_INF_PATH_CLASSES + "/"
						+ ConfigParams.HIBERNATE_TEMPLATE_XML);
				setProblem(null);
			}

		} catch (Exception e) {
			setProblem("无法读取和配置 Hibernate 配置文件模版 "
					+ ConfigParams.HIBERNATE_TEMPLATE_FILE);
		}

		setBusy(false);
	}

}