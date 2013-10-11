package setup.swing;

/**
 * 配置参数.
 * @author BeanSoft
 *
 */
public interface ConfigParams {
	/** Hibernate 模版文件 */
	public static final String HIBERNATE_TEMPLATE_FILE = "/hibernate.cfg.txt";
	/** Hibernate 配置文件名称 */
	public static final String HIBERNATE_TEMPLATE_XML = "/hibernate.cfg.xml";
	/** JDBC 驱动类名 */
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/** Web 应用的 Root 目录 */
	public static final String WEBROOT = "WebRoot";
	/** classes 目录 */
	public static final String WEB_INF_PATH_CLASSES = WEBROOT + "/WEB-INF/classes";
	/** WAR 文件名称 TODO 请修改此目录 */
	public static final String WAR_NAME = "struts2sh";
	/** 数据库连接编码 */
	public static final String ENCODING = "UTF-8";
	/** MySQL URL 编码参数 */
	public static final String MYSQL_ENCODING = "?useUnicode=true&characterEncoding=" + ENCODING;
}