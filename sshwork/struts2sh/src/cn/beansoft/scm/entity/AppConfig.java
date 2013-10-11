package cn.beansoft.scm.entity;

/**
 * AppConfig entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppConfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private String appTitle;
	private String badwords;
	private String afficheTitle;
	private String afficheContent;
	private String copyright;

	// Constructors

	/** default constructor */
	public AppConfig() {
	}

	/** minimal constructor */
	public AppConfig(String appTitle) {
		this.appTitle = appTitle;
	}

	/** full constructor */
	public AppConfig(String appTitle, String badwords, String afficheTitle,
			String afficheContent, String copyright) {
		this.appTitle = appTitle;
		this.badwords = badwords;
		this.afficheTitle = afficheTitle;
		this.afficheContent = afficheContent;
		this.copyright = copyright;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppTitle() {
		return this.appTitle;
	}

	public void setAppTitle(String appTitle) {
		this.appTitle = appTitle;
	}

	public String getBadwords() {
		return this.badwords;
	}

	public void setBadwords(String badwords) {
		this.badwords = badwords;
	}

	public String getAfficheTitle() {
		return this.afficheTitle;
	}

	public void setAfficheTitle(String afficheTitle) {
		this.afficheTitle = afficheTitle;
	}

	public String getAfficheContent() {
		return this.afficheContent;
	}

	public void setAfficheContent(String afficheContent) {
		this.afficheContent = afficheContent;
	}

	public String getCopyright() {
		return this.copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

}