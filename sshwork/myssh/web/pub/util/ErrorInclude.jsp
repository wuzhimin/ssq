<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 


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


