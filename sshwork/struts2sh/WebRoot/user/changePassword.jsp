<%@ page  pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<c:if test="${username == null}">
<%
// 登录判断
response.sendRedirect("../login.jsp");
%>
</c:if>

<HTML>
<HEAD>

<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>


<TITLE>${appConfig.appTitle} - 修改密码</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="../inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->
<form action="user/changePassword.action" method="post" class='required-validate'>
<TABLE border=0 cellSpacing=0 cellPadding=0 width=400 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <h1>修改密码</h1>    
      <font color=red>${message}</font>  </TD>
    </TR>
	<tr>
	<td width="50%">用户名:</td>
	<td width="50%">${loginedUser.name}</td>
	</tr>
	<tr>
	<td width="50%">旧密码:</td>
	<td width="50%"><input name="password" type="password" class="required min-length-6 max-length-20" ></td>
	</tr>
	<tr>
      <td>新密码: </td>
	  <td><input name="password_new" type="password" class="required min-length-6 max-length-20" ></td>
	  </tr>
		<tr>
          <td>新密码确认: </td>
		  <td><input name="password_new_repeat" type="password" class="required equals-password_new" id="password2" ></td>
	  </tr>
	<tr>
	<td width="50%"><input type="submit" value="修改密码"></td>
	<td width="50%">&nbsp;</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
	  <td>没有账户?请<a href="reg.jsp">注册</a></td>
	  <td>忘记密码?请点击<a href="retrievePassword.htm">取回密码</a></td>
	  </tr>
  </TBODY>
</TABLE>
</form>

<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>