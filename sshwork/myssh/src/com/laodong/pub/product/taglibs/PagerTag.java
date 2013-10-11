package com.laodong.pub.product.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.laodong.pub.util.base.Pager;
import com.laodong.pub.util.javabeans.Utility;

public class PagerTag extends TagSupport
{
	  private String csContextPath = "";
	  private String csImgPath = "";
//
	  public PagerTag()
	  {
	  }

	  public int doStartTag()  throws JspException
	  {
	    try{
	      HttpServletRequest req = (HttpServletRequest)pageContext.getRequest();
	      csContextPath = req.getContextPath();
	      csImgPath=csContextPath+"/images/";
	      Pager pager = (Pager)req.getAttribute("Pager");
	      JspWriter jspwriter = pageContext.getOut();
	      String allSizeString = Utility.getMessage("总记录数");
	      if(pager.getSize()==0){
	        jspwriter.print(allSizeString+"0");
	      }else{
	        jspwriter.print(allSizeString+pager.getSize()+"&nbsp;&nbsp;");
	        jspwriter.print(Utility.getMessage("当前记录")+pager.getBegin()+"-"+pager.getEnd()+"&nbsp;&nbsp;");
	        if(pager.getBegin()>1)
	          jspwriter.print("<a href=\"javascript: var form=document.forms[0]; form.pagerOffSet.value='"+(pager.getBegin()-pager.getLength())+"'; form.strutsAction.value='1';form.submit();\">"+Utility.getMessage("上一页")+"</a>");
	        if(pager.getEnd()<pager.getSize())
	          jspwriter.print("&nbsp;<a href=\"javascript: var form=document.forms[0]; form.pagerOffSet.value='"+(pager.getEnd()+1)+"'; form.strutsAction.value='1';form.submit();\">"+Utility.getMessage("下一页")+"</a>");  
	      }
	    }//end of try
	    catch(Exception ex)
	    {
	      ex.printStackTrace();
	      throw new JspTagException(ex.getMessage());
	    }
	    return 0;  
	  }  
	}