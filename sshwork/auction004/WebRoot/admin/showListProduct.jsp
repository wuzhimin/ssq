<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showListProduct.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align:center">
<div style="width:500px; height:auto">
<span style="float:left"><h3>在线拍卖系统</h3></span>
<span style=" float: right"><h4><s:if test="#session.users==null"><a href="loginUsers.jsp">登录</a></s:if>
<s:if test="#session.users!=null"><a href="exitUsers">注销</a></s:if>|<a href="#">注册</a></h4></span>

<div>
<div align="left" style=" clear: left;"><a href="${path}admin/addProduct.jsp">新增</a></div>
<table width="571" height="81" border="1" >
  <tr align="center" bgcolor="red">
    <td width="81">姓名</td>
    <td width="106">描述</td>
    <td width="100">开始时间</td>
    <td width="90">结束时间</td>
    <td width="65">起拍价</td>
    <td width="65">操作</td>
  </tr>
  <s:iterator value="list">
  <tr align="center">
    <td><s:property value="pname"/></td>
    <td><s:property value="pdesc"/></td>
    <td><s:property value="pbegin_date"/></td>
    <td><s:property value="pend_date"/></td>
    <td><s:property value="pstart_price"/></td>
    <td>
    <s:if test="#session.users!=null"><a href="showProductById?product.pid=<s:property value="pid"/>">修改</a>|
    <a href="delProduct?product.pid=<s:property value="pid"/>">删除</a></s:if>
    </td>  
    
  </tr>
  </s:iterator>
</table>
</div>
</div>
</body>
</html>
