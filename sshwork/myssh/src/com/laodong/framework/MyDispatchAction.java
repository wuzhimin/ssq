package com.laodong.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MyDispatchAction extends DispatchAction{
   private static ThreadLocal local = new ThreadLocal();
   public static void set(Object obj){
	   local.set(obj);
   }
   public static Object get(){
	   return local.get();
   }
   public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
	   HttpSession session = request.getSession();
	   String userid = (String)session.getAttribute("userid");
	   System.out.println("------放入线程本地变量的当前用户名: " + userid);
	   set(userid);
	   return super.execute(mapping, form, request, response);
   }

}
