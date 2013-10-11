<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 
<%@ page import="com.laodong.pub.util.StringUtil"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<html>
   <head> 
    	<link rel="stylesheet" href="<%=request.getContextPath()%>\css\laodong.css" type="text/css">
   </head>
   <body>
 
    	<br><br>
<br>
<br><br>

<%
String lsErrorMsg="";
if(request.getAttribute("myerror")!=null)
  lsErrorMsg = (String)request.getAttribute("myerror");
if(!lsErrorMsg.equals("")){
%>
<table cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td colspan=2>提示信息：<font color="red"><%=lsErrorMsg%></font></td>
  </tr>
</table>
<%
}
%>
 

<%
String myMsg="";
if(request.getParameter("mymessage")!=null)
  myMsg = (String)request.getParameter("mymessage");
if(!myMsg.equals("")){
 
 
  
    //网站上必须放开这2行 
      //byte[] temp_t=myMsg.getBytes("ISO8859-1");   
	    //myMsg=new String(temp_t,"gb2312"); 
%>
<table cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td colspan=2>提示信息：<font color="red"><%=myMsg%></font></td>
  </tr>
</table>
<%
}
%>
 


<%
String lsMyMsg="";
if(request.getAttribute("mymessage")!=null)
  lsMyMsg = (String)request.getAttribute("mymessage");
if(!lsMyMsg.equals("")){
%>
<table cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td colspan=2>提示信息：<font color="red"><%=lsMyMsg%></font></td>
  
  </tr>
</table>
<%
}
%>
 
 

<%
String sessmsg="";
if(session.getAttribute("mymessage")!=null&&StringUtil.sfWz(lsErrorMsg)&&StringUtil.sfWz(myMsg)&&StringUtil.sfWz(lsMyMsg))
  sessmsg = (String)session.getAttribute("mymessage");
if(!sessmsg.equals("")){
%>
<table cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td colspan=2>提示信息：<font color="red"><%=sessmsg%></font></td>
  
  </tr>
</table>
<%
}
%>
 

<br>
<div align=center><input type=button value="返回"
onclick="javascript:history.go(-1)"/></div>     
   </body>
<html>                                                             

