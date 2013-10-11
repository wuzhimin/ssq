<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - ${title}</TITLE>


</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="../inc_head.jsp" %>
<!-- 头部页面结束 -->

<p class="h1" align="center">${title}</p>

<p class="h2" align="center">${message}</p>

<!-- 页面主体 -->
<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
<tr style="font-weight:bold">
<td>编号</td>
<td>交易日期</td>
<td>金额</td>
<td>状态</td>
</tr>

<c:forEach items="${orders}" var="order">
<tr>

<td>${order.id} <a href="order/viewOrderDetail.action?id=${order.id}" target="_blank">详细</a></td>
<td>${order.addDate}</td>
<td>${order.cost}</td>
<td><c:if test="${order.status == 0}">未支付 <a href="order/pay.action?id=${order.id}">TODO现在支付</a></c:if>
<c:if test="${order.status == 1}">已支付</c:if>
</td>
</tr>

</c:forEach>
</TABLE>


<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
