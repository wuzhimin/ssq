<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 

<link rel="stylesheet" href="<%=request.getContextPath()%>\css\laodong.css" type="text/css">

<%
String lsMyMsg="";
if(request.getAttribute("mymessage")!=null)
  lsMyMsg = (String)request.getAttribute("mymessage");
  if(!lsMyMsg.equals("")){
%>
 
<table width="100%" height="
100%">
	<tr>
		<td align="center">
      <table valign="center" align="center" border="0" width="100%" >
        <tr >
          <td  align="center">
          	 提示信息：<font color="red"><%=lsMyMsg%></font></td>
          </tr>
 
      </table>
 
    </td>
  </tr>
  <tr>
    <td height="40%" align="center"></td>
  </tr>
</table>
<%
 }
 %>

