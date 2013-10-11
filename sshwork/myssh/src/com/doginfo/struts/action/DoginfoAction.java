package com.doginfo.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.doginfo.service.DoginfoService;
import com.doginfo.struts.dao.DoginfoDAO;
import com.doginfo.struts.form.DoginfoForm;
import com.doginfo.struts.obj.DoginfoObj;
import com.laodong.pub.util.StringUtil;
import com.laodong.pub.util.dao.NLLDDAOTool;

public class DoginfoAction extends DispatchAction{
	/***********狗登记信息管理*************/
	public ActionForward doginfolist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){	
		DoginfoDAO dao = (DoginfoDAO)NLLDDAOTool.getBean("doginfoDAO");
		DoginfoForm cform = (DoginfoForm)form;
		DoginfoObj obj = cform.getObj();
		try {	
            StringBuffer wheresql = new StringBuffer(" ");
            if(StringUtil.sfYz(obj.getName())){
            	wheresql.append(" and name = '" + obj.getName() + "'");
            }
            if(StringUtil.sfYz(obj.getMaster())){
            	wheresql.append(" and master = '" + obj.getMaster() + "'");
            }
//			List list = dao.findAllDoginfoObjList();
            List list = dao.findAllDoginfoObjByCon(wheresql.toString());
			request.setAttribute("list", list);
		} catch (Exception e) {
			request.setAttribute("myerror", e.getMessage());
			e.printStackTrace();
		}
		return mapping.findForward("doginfolist");
	}
	public ActionForward adddoginfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("adddoginfo");
	}
	public ActionForward adddoginfosave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DoginfoService dao = (DoginfoService)NLLDDAOTool.getBean("DoginfoService");
		DoginfoForm cform = (DoginfoForm)form;
		DoginfoObj obj = cform.getObj();
		try {
			dao.taxInsDoginfoObj(obj);
			request.setAttribute("myerror", "新增成功！");
		} catch (Exception e) {
			request.setAttribute("myerror", e.getMessage());
			e.printStackTrace();
		}
		return doginfolist(mapping, form, request, response);
	}

	public ActionForward upddoginfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DoginfoDAO dao = (DoginfoDAO)NLLDDAOTool.getBean("doginfoDAO");
		DoginfoForm cform = (DoginfoForm)form;
		int id = StringUtil.getId(request, "id");
		try {
			DoginfoObj obj = dao.findDoginfoObjById(id);
			cform.setObj(obj);
		} catch (Exception e) {
			request.setAttribute("myerror", e.getMessage());
			e.printStackTrace();
		}
		return mapping.findForward("upddoginfo");
	}
	public ActionForward upddoginfosave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DoginfoDAO dao = (DoginfoDAO)NLLDDAOTool.getBean("doginfoDAO");
		DoginfoForm cform = (DoginfoForm)form;
		DoginfoObj obj = cform.getObj();
		try {
			String[][] attrs = {
					{"name","String",obj.getName()}
			};
			dao.updDoginfoObj(obj.getId(), attrs);
			request.setAttribute("myerror", "修改成功！");
		} catch (Exception e) {
			request.setAttribute("myerror", e.getMessage());
			e.printStackTrace();
		}
		return doginfolist(mapping, form, request, response);
	}
	public ActionForward deldoginfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DoginfoDAO dao = (DoginfoDAO)NLLDDAOTool.getBean("doginfoDAO");
		DoginfoForm cform = (DoginfoForm)form;
		int id = cform.getObj().getId();
		try {
			dao.delDoginfoObj(id);
			request.setAttribute("myerror", "删除成功！");
		} catch (Exception e) {
			request.setAttribute("myerror", e.getMessage());
			e.printStackTrace();
		}
		return doginfolist(mapping, form, request, response);
	}
	public ActionForward viewdoginfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DoginfoDAO dao = (DoginfoDAO)NLLDDAOTool.getBean("doginfoDAO");
		DoginfoForm cform = (DoginfoForm)form;
		int id = StringUtil.getId(request, "id");
		try {
			DoginfoObj obj = dao.findDoginfoObjById(id);
			cform.setObj(obj);
		} catch (Exception e) {
			request.setAttribute("myerror", e.getMessage());
			e.printStackTrace();
		}
		return mapping.findForward("viewdoginfo");
	}
}
