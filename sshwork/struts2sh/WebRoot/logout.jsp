<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.Enumeration"%>
<%-- 包含标签库 --%>
<%@ include file="inc_taglib.jsp" %>
<%
// 清空 session 内容
Enumeration<String> items = session.getAttributeNames();

while(items.hasMoreElements()) {
	session.removeAttribute(items.nextElement());
}

%>
<!--  设置标题 -->
<c:set var="title" scope="request" value="退出系统" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <title>退出 ${appConfig.appTitle}</title>
    
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="inc_resources.jsp" %>
    
  </head>
  <body>
<!-- 头部页面开始 -->
<%@ include file="inc_head.jsp" %>

<!-- 头部页面结束 -->
  
  <TABLE border=0 cellSpacing=0 cellPadding=0 width=400 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <p class="h1">您已经成功退出! </p>
  <p>您现在可以: </p>
  <ul>
    <li><a href="login.jsp">重新登录</a></li>
    <li><a href="index.jsp">返回首页</a></li>
  </ul>  </TD>
    </TR>
    </TBODY>
    </TABLE>
    
<!-- 底部页面开始 -->
<%@ include file="inc_foot.jsp" %>
<!-- 底部页面结束 -->  
  </body>
</html>