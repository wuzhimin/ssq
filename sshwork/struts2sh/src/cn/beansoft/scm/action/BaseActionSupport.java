package cn.beansoft.scm.action;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import cn.beansoft.scm.dao.BaseDAO;
import cn.beansoft.scm.entity.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 封装一些基本的Action功能.
 * @author BeanSoft
 *
 */
public abstract class BaseActionSupport extends ActionSupport {
	// 返回的消息
	private String message;
	private String title;// 页面显示标题
	private BaseDAO baseDAO;// 基础的DAO对象
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	/**
	 * 将所有URL参数合并成一个URL字符串(page参数除外), 提供分页时显示.
	 * @return 字符串, 如: para1=11&para2=bb
	 */
	public String mergeParamsAsURI() {
		Map<String, String[]> params = getRequest().getParameterMap();
		
		StringBuffer out = new StringBuffer();
		
		Set<String> keys = params.keySet();
		
		for (String key : keys) {
			if(!"page".equals(key)) {
				// TODO 发现Map值有乱码
				String[] values = params.get(key);
				
				if(values.length > 1) {
					values = getRequest().getParameterValues(key);
				} else {
					values = new String[] {getParameter(key)};
				}
				
				System.out.println("key=" + key);
				
				try {
					for(String value : values) {
						System.out.println("value=" + value);
						
						out.append(java.net.URLEncoder.encode(key, "UTF-8") + "=");
						out.append(java.net.URLEncoder.encode(value, "UTF-8") + "&");
					}

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 删除末尾多余的 & 字符
		if(out.toString().endsWith("&")) {
			out.deleteCharAt(out.length() - 1);
		}
		
		return out.toString();
	}

	/**
	 * 重置和页面显示有关的参数, title和message属性都设置为空.
	 */
	void resetMessages() {
		setMessage("");
		setTitle("");
	}
	
	/**
	 * 获取当前会话的登录用户信息
	 * @return User
	 */
	public User getSessionLoginedUser() {
		User currentUser = (User) getSession("loginedUser");
		return currentUser;
	}
	
	/**
	 * 读取表单参数
	 * @param name
	 * @return
	 */
	public String getParameter(String name) {
		return ServletActionContext.getRequest().getParameter(name);
	}
	
	/**
	 * 将表单参数作为整数返回.
	 * @param name 表单参数名
	 * @return
	 */
	public int getParameterInt(String name) {
		String s = getParameter(name);
		
		if(s == null) {
			return 0;
		} else {
			try {
				return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	/**
	 * 将表单参数作为长整数返回.
	 * @param name 表单参数名
	 * @return
	 */
	public long getParameterLong(String name) {
		String s = getParameter(name);
		
		if(s == null) {
			return 0L;
		} else {
			try {
				return Long.parseLong(s);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0L;
	}	
	
	/**
	 * 设置 request 的属性.
	 * @param name 属性名
	 * @param value 属性值
	 */
	public void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}
	
	/**
	 * 获取 request 的属性.
	 * @param name 属性名
	 */
	public Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}	
	
	/**
	 * 读取 session 中的属性值
	 * @param name
	 * @return
	 */
	public static Object getSession(String name) {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();

		return session.get(name);
	}
	
	/**
	 * 向 session 设置属性值
	 * @param name
	 * @param value
	 */
	public static void setSession(Object name, Object value) {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		session.put(name, value);
	}
	
	/**
	 * 获取 application 对象.
	 * @return
	 */
	public static ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}
	
	/**
	 * 获取请求对象.
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	

}
