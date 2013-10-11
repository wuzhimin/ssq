<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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

<head>
<title>会员登录 - 会员中心</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!--  <link href="/Content/style.css" rel="stylesheet" type="text/css" /> -->


<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.md5.js"></script>

<!-- <script type="text/javascript" src="/scripts/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="/scripts/csdn.js"></script>
<script type="text/javascript" src="/scripts/jquery.cookie.js"></script>
<script type="text/javascript" src="/scripts/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="/scripts/login.js"></script>
 -->

<style type="text/css">
.div table td .inputbox {
	border-radius: 5px;
	width: 300px;
}
</style>

<script type="text/javascript">
	
	$(document).ready(function(){
       	// 重置
        $("#loginform")[0].reset();
     });
            
	function md5pwd() {
	    // 把密码加密
	    if($("#pwd").val()==null || $("#pwd").val()=="") {
	    	return;
	    } else {
			$("#pwd").val($.md5($("#pwd").val()));
		}
	}
	
</script>

</head>
<body>
	<div class="div">
		<s:form id="loginform" action="user" method="post" theme="simple">
			<table>
				<tr>
					<td width="100">用户名：</td>
					<td><input type="text" id="uname" name="uname"
						class="inputbox" maxlength="100" /></td>
				</tr>
				<tr>
					<td width="100">密 码：</td>
					<td><input type="password" id="pwd" name="pwd" class="inputbox"
						maxlength="50" /></td>
				</tr>

				<tr>
					<td></td>
					<td>
						<s:submit name="login" value="登录" align="left" method="login" 
							onclick="md5pwd();" /> 
						<s:submit name="forgetpwd" value="忘记密码"
							align="left" method="" />
					</td>
					<td><s:a href="register.jsp">注册</s:a></td>	
				</tr>

			</table>
			
			<span id="sp_err" style="color:#ff0000;">${txtinfo}</span>
		</s:form>
	</div>

	<input id="f" type="hidden" value='' />
	<input id="cb" type="hidden" value='logined' />

	<!-- <script type="text/javascript" src="/scripts/csdn.loading.js"></script> -->
</body>
</html>
