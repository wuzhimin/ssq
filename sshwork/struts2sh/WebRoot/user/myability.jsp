<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<c:if test="${username == null}">
<c:set var="message" value="对不起,您尚未登录!" />
</c:if>

<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - 查看我的权限</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="../inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->


<TABLE  border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <font color=red size=5>${message}</font><br>
      <span class="STYLE6">查看用户权限</span></TD>
    </TR>
	<tr>
	<td width="50%">用户名:</td>
	<td width="50%">${loginedUser.name}</td>
	</tr>
	<tr>
	<td width="50%">真实姓名:</td>
	<td width="50%">${loginedUser.realName}</td>
	</tr>

<tr>
	<td width="50%">用户权限:</td>
	<td width="50%">
	<c:if test="${loginedUser.userType == 0}">普通用户, 可以订购商品, 不能添加供应商, 不能提交商品</c:if>
  <c:if test="${loginedUser.userType == 1}">网上交易员, 不能订购商品, 不能添加供应商, 可以审核供应商, 审核商品, 修改提成</c:if>
  <c:if test="${loginedUser.userType == 2}">供应商, 不能订购商品, 可以添加供应商, 修改供应商信息, 提交商品, 修改打折信息</c:if>
  <c:if test="${loginedUser.userType == 3}">财务经理</c:if>
  <c:if test="${loginedUser.userType == 4}">系统管理员</c:if>
	</td>
	</tr>	

  </TBODY>
</TABLE>

<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
