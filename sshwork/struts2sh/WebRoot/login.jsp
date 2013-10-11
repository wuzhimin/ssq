<%@ page  pageEncoding="UTF-8"%>
<%-- 包含标签库 --%>
<%@ include file="inc_taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>

<TITLE>${appConfig.appTitle} - 登录</TITLE>

<!-- JS, CSS, BASE 标记等 -->
<%@ include file="inc_resources.jsp" %>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->
<form action="user/login.action" method="post" class='required-validate'>
<TABLE border=0 cellSpacing=0 cellPadding=0 width=400 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <h1>请登录</h1>    <font color=red>${message}</font>  </TD>
    </TR>
	<tr>
	<td width="50%">用户名:</td>
	<td width="50%"><input name="user.name" value="${user.name}" class="required min-length-5 max-length-20 validate-alphanum"></td>
	</tr>
	<tr>
	<td width="50%">密码:</td>
	<td width="50%"><input name="user.password" type="password"  value="${user.password}" class="required min-length-6 max-length-200" ></td>
	</tr>
		<tr>
	<td width="50%">登录类型:</td>
	<td width="50%"><select name="user.userType" id="userType">
	  <option value="0" selected>普通</option>
	  <option value="1">供应商</option>
	  <option value="2">网上交易员</option>
	  <option value="3">财务经理</option>
	  <option value="4">系统管理员</option>
	</select>	</td>
	</tr>
	<tr>
	<td width="50%"><input type="submit" value="登录"></td>
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
<%@ include file="inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>