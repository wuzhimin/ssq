<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="true" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>


  ggg:<s:property value="txtinfo" />
  <s:if  test="%{#request.txtinfo!=nul}" ><s:property value="#request.txtinfo" /></s:if>
  <s:elseif  test="%{#session.username!=nul}" >用户已登录</s:elseif>
  <br>
  <span id="sp_err" style="color:#ff0000;">当前用户： <s:property value="#session.username" /> </span>
  </body>
</html>
