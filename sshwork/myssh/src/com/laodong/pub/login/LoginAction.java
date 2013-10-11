package com.laodong.pub.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.laodong.pub.product.spring.UserService;
import com.laodong.pub.pubinf.PubinfConstants;
import com.laodong.pub.util.dao.NLLDDAOTool;

public class LoginAction extends DispatchAction {
	/** 注册 */
	public ActionForward regist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LoginForm myform = (LoginForm) form;

		UserDAO dao = (UserDAO) NLLDDAOTool.getBean("lgUserDAO");
		try {
			LoginObj obj = dao.findLoginObjByUserid(myform.getUserid());
			if (obj != null) {
				request.setAttribute("myerror", "此用户已存在！");
				return mapping.findForward("login");
			}

			request.setAttribute("myerror", "注册成功！");
		} catch (Exception e) {
			request.setAttribute("myerror", "注册失败！");
			e.printStackTrace();
		}
		return mapping.findForward("login");
	}
	/** 初始化修改密码 */
	public ActionForward updpassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String userid = (String) session
				.getAttribute(PubinfConstants.userLonginName);
		if (userid == null) {
			request.setAttribute("mymessage", "请您首先登录系统，才能进入本页！");
			return mapping.findForward("FAILURE");
		}
		return mapping.findForward("updpassword");
	}
	/** 修改密码 */
	public ActionForward updpasswordsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String userid = (String) session
				.getAttribute(PubinfConstants.userLonginName);
		LoginForm myform = (LoginForm) form;

		UserDAO dao = (UserDAO) NLLDDAOTool.getBean("lgUserDAO");
		try {
			LoginObj obj = null;
			if (obj == null) {
				request.setAttribute("myerror", "旧密码不对！");
				return mapping.findForward("login");
			}
			dao.updpassword(userid, myform.getNewpassword());
			request.setAttribute("myerror", "修改成功！");
		} catch (Exception e) {
			request.setAttribute("myerror", "修改失败！");
			e.printStackTrace();
		}
		myform.setPassword(null);
		myform.setNewpassword(null);
		return mapping.findForward("updpassword");
	}

	/**
	 * @方法名称 临时测试spring的方法
	 * @业务描述
	 * 
	 * @author lhh
	 * @时间 2007-7-12 21:55:26
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mymz", "haha1");

		UserService user = (UserService) NLLDDAOTool.getBean("UserService");
		try {
			user.taxd12();
			// user.ptd12();
			// TestJdbc.aa();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("login");
	}

	/** 登录 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		LoginForm myform = (LoginForm) form;

//		UserDAO dao = (UserDAO) NLLDDAOTool.getBean("lgUserDAO");
//
//		try {
//			LoginObj obj = dao.findLoginObjByUserid(myform.getUserid());
//			if (obj == null) {
//				request.setAttribute("myerror", "用户不存在！");
//				return mapping.findForward("login");
//			}
//
//			if (obj == null) {
//				request.setAttribute("myerror", "密码错误！");
//				return mapping.findForward("login");
//			} else {
//				HttpSession session = request.getSession(true);
//				session.setAttribute(PubinfConstants.userLonginName, obj
//						.getUserid());
//				session.setAttribute(PubinfConstants.userInjzdeptid, "" + 0);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		HttpSession session = request.getSession(true);
		session.setAttribute("userid", myform.getUserid());
		return mapping.findForward("login");
	}

	/** 退出 */
	public ActionForward loginOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		String userid = (String) session
				.getAttribute(PubinfConstants.userLonginName);
		try {

		} catch (Exception e) {

			e.printStackTrace();
		}
		session.invalidate();
		request.setAttribute("myerror", "成功退出！");
		return mapping.findForward("login");
	}
	public ActionForward myindex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("myindex");
	}

}
