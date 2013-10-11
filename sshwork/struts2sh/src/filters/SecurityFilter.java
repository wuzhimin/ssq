package filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.beansoft.scm.dao.BaseDAO;
import cn.beansoft.scm.dao.ResourceDAO;
import cn.beansoft.scm.entity.Resource;
import cn.beansoft.scm.entity.User;


/**
 * URL安全访问权限过滤器.
 * @author BeanSoft
 * @see ResourceDAO
 */
public class SecurityFilter implements Filter {
	private ServletContext application;
	private static String errorMessage = "对不起, 您的权限不足, 请使用合适的帐号登录!";

	private BaseDAO getBaseDAO() {
		return (BaseDAO) WebApplicationContextUtils
				.getRequiredWebApplicationContext(
						application).getBean(
						"BaseDAO");
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 取出相对路径, 输入是 /beijingscm/admin/index.jsp --> /admin/index.jsp
		HttpServletRequest request= (HttpServletRequest)req;
		BaseDAO dao = getBaseDAO();
		User currentUser = (User)request.getSession().getAttribute("loginedUser");// 获取会话中的当前登录用户.
		
		int roleId = currentUser == null ? -1 : currentUser.getUserType();
		
		String loginMessage = "";// 登录提示信息
		if(currentUser == null ) {
			loginMessage = " 您当前尚未登录.";
		}
		
		
		String requestPath = request.getRequestURI();// /beijingscm/admin/index.jsp
		String contextPath = request.getContextPath();// /beijingscm/
		String relativePath = requestPath.substring(contextPath.length());// /admin/index.jsp 相对路径
		String folderPath = null;// 目录名 /admin/index.jsp --> 输出 /admin/, /index.jsp --> 输出空
		int 第二个斜线位置 = relativePath.indexOf('/', 1);
		
		if(第二个斜线位置 > 0) {
			folderPath = relativePath.substring(0, 第二个斜线位置 + 1);
			folderPath += "*";
		}
		
//		System.out.println("第二个斜线位置=" + 第二个斜线位置);
//		System.out.println("folderPath=" + folderPath);
//		System.out.println("contextPath=" + contextPath);
//		System.out.println("relativePath=" + relativePath);
		
		String 查看资源是否公开HQL = "select count(*) from Resource res where res.uri = ? or res.uri = ?" ;
		String 查看资源是否对当前用户公开HQL = "select count(*) from Resource res where (res.uri = ? or res.uri = ?) and res.scmRole.id = ?";
		
		if(dao.queryForCount(查看资源是否公开HQL, relativePath, folderPath) > 0) {
			System.out.println("资源不公开");
			
			if(dao.queryForCount(查看资源是否对当前用户公开HQL, relativePath, folderPath, roleId) > 0) {
				chain.doFilter(req, resp);				
			} else {
				
				if("true".equalsIgnoreCase(request.getParameter("ajax"))) {
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("text/html;charset=UTF-8");
					
					resp.getWriter().print(errorMessage + loginMessage);
				} else {
					request.setAttribute("message", errorMessage + loginMessage);
					request.getRequestDispatcher("/error.jsp").forward(request, resp);					
				}
				
			}
			
		} else {
			chain.doFilter(req, resp);
		}
				
	}

	public void init(FilterConfig config) throws ServletException {
		application = config.getServletContext();
	}

}