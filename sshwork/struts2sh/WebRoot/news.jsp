<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="inc_resources.jsp" %>

<!--  设置标题 -->
<c:set var="title" scope="request" value="新闻" />

<TITLE>${appConfig.appTitle} - ${title}</TITLE>


</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->
<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
<tr>
<td>BeanSoft公司再度当选2009年福布斯财富500强, 并成为London 2012 Olympic Games Golden Partner.<br>

<img src='images/supply_logo.png'>
</td>
</tr>
</TABLE>

<!-- 底部页面开始 -->
<%@ include file="inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
