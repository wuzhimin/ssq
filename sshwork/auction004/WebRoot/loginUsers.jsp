<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println("");
%>


<html>

<link rel="stylesheet" type="text/css" href="<%=path %>/css/test.css">

<head>

<base href="<%=basePath%>">

<title>My JSP 'loginUsers.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	用户登录
	<br>
	<s:form action="loginUsers" theme="simple">
  用户名 <s:textfield label="用户名" name="uname"></s:textfield>
		<br>
   密码<s:password label="密码" name="upwd"></s:password>
		<br>
		<s:submit value="登录"></s:submit>
		<s:reset value="取消"></s:reset>
	</s:form>
</body>
</html>
