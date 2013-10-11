<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<script>
function confirmDelete() {
	return confirm("数据删除后不可恢复, 您确定要继续?");
}
</script>
<TITLE>${appConfig.appTitle} - 注册用户列表</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<p class="h1">管理 - 注册用户列表</p>

<!-- 页面主体 -->
<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="9" align="center">
      <font color=green size=5>${message}</font>
共 ${pageBean.recordCount} 条记录 ${pageBean.pageCountHtml} 每页显示 ${pageBean.pageCount } 条<br>      
       ${pageBean.javaScriptJumpCode}
  ${pageBean.pageJumpLinkHtml}
  ${pageBean.pageFormJumpHtml} </TD>
    </TR>
	<tr bgcolor="gold" style="font-weight:bold">
	  <td>用户名</td>
	  <td>性别</td>
	  <td>Email</td>
	  <td>主页</td>
	  <td>操作</td>
	  <td>注册时间</td>
	  <td>激活</td>
	  <td>类型</td>
	  <td>登录次数</td>
	 </tr>
 <c:forEach items="${users}" var="user" >
  <tr>
  <td><a href="admin/findUserById.action?id=${user.id}" title="查看用户详细信息" target="_blank">${user.name}</a></td>
  <td><c:if test="${user.gender == 0}">男</c:if>
  <c:if test="${user.gender == 1}">女</c:if>
  </td>
  <td>${user.email}</td>
  <td><a href="${user.website}">${user.website}</a></td>
  <td><c:if test="${user.userType != 4}"> 
  <a href='admin/deleteUser.action?id=${user.id}' title="删除此用户, 注意管理员用户不可删除" onclick="return confirmDelete()"><img src='images/icons/delete.gif' align='absmiddle' border=0>删除</a></c:if>
  <a href='admin/toEditUser.action?id=${user.id}' title="修改用户详细信息" ><img src='images/icons/edit.gif' align='absmiddle' border=0>修改</a>
 </td>
  <td>${user.regDate}</td>
  <td>${user.active}</td>
  <td>	<c:if test="${user.userType == 0}">普通</c:if>
  <c:if test="${user.userType == 1}">网上交易员</c:if>
  <c:if test="${user.userType == 2}">供应商</c:if>
  <c:if test="${user.userType == 3}">供应商</c:if>
  <c:if test="${user.userType == 4}">系统管理员</c:if></td>
  
  <td>${user.loginCount}</td>
  </tr>
  </c:forEach>	 
  

	 
  </TBODY>
</TABLE>

<h3>相关操作</h3>

<a href='admin/user_add.jsp' title="快速添加新用户">
<img src='images/icons/new.gif' border=0 align='absmiddle'>添加新用户</a>
</BODY>
</HTML>
