<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>


<TITLE>${appConfig.appTitle} - 注册用户列表</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">

<!-- 头部页面开始 -->
<%@ include file="../inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->
<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="6"align="center">
      <font color=green size=5>${message}</font><br>
      注册用户列表<br>
      共 ${pageBean.recordCount} 条记录 ${pageBean.pageCountHtml} 每页显示 ${pageBean.pageCount } 条<br>
       ${pageBean.javaScriptJumpCode}
  ${pageBean.pageJumpLinkHtml}
  ${pageBean.pageFormJumpHtml}</TD>
    </TR>
	<tr bgcolor="gold" style="font-weight:bold">
	  <td>用户名</td>
	  <td>性别</td>
	  <td>Email</td>
	  <td>主页</td>
	  <td>地址</td>
	  <td>注册时间</td>
	 </tr>
 <c:forEach items="${users}" var="user" >
  <tr>
  <td><a href="user/findById.action?id=${user.id}">${user.name}</a></td>
  <td><c:if test="${user.gender == 0}">男</c:if>
  <c:if test="${user.gender == 1}">女</c:if>
  </td>
  <td>${user.email}</td>
  <td><a href="${user.website}">${user.website}</a></td>
  <td>${user.address}</td>
  <td>${user.regDate}</td>
  </tr>
  </c:forEach>	 
	 
  </TBODY>
</TABLE>


<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
