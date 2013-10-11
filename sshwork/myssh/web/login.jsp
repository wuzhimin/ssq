<%@ page language="java" contentType="text/html;charset=GB2312"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
    <title>login.jsp</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/laodong.css" type="text/css">
    <SCRIPT language=JavaScript>
      <!-- 
     function regist(){
   			window.location.href="regist.jsp";;		
   		}
   		function login(){
   			document.loginForm.action="<%=request.getContextPath()%>/login.do?method=login";
   	    document.loginForm.submit();		
   		}
   		function enter(){
   			window.location.href="index.jsp";		
   		}
   		function query(){
   			document.loginForm.action="<%=request.getContextPath()%>/login.do?method=query";
   	    document.loginForm.submit();
   		}
      -->
    </SCRIPT>
  </head>
  <body TEXT="#336699" BGPROPERTIES=FIXED>
    <html:form action="/login">
    	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    	<% pageContext.include("/pub/util/ErrorInclude.jsp"); %>	
    	<div style="LINE-HEIGHT: 29px;" align=center><font size=2>登录系统</font><div>
    		<br>
     <table width="30%" border="0">
      <tr>
         <td align="right">用&nbsp;&nbsp;&nbsp;&nbsp;户:</td>
         <td><html:text property="userid" style="width:176;"/>&nbsp;&nbsp;<a href="javascript:regist()"><font color=red>注册</font></a></td>
      </tr>
      <tr>
         <td align="right">密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
         <td><html:password property="password" style="width:176;" redisplay="true" onkeydown="tt()"/></td>
      </tr>
    <tr><td colspan=2 align="center">&nbsp;</td></tr>
      <tr>
         <td colspan=2 align="center">
         	<input type="button" value="登录" onclick="login()"/>&nbsp;
  	      <input type="button" value="游客" onclick="enter()"/>&nbsp;
  	      <!--<input type="button" value="测试" onclick="query()">-->
  	     </td>
      </tr>
    </table> 
    </html:form>
    <script language="javascript">
    	document.all("userid").focus();
    	function tt(){
    		if(event.keyCode=="13")
    		  login();
    	}
    </script>
  </body>
</html>
