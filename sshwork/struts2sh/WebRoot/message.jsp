<%@ page  pageEncoding="UTF-8" %>
<!-- 消息显示页面,需要两个属性: title 和 message -->

<%-- 包含标签库 --%>
<%@ include file="inc_taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - ${title}</TITLE>
</HEAD>

<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->

<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD align="center">
	  <p class="h1">${title}</p><br>
	  <font color=green size=5>${message}</font><br>
	  
	  <c:if test="${returnURL != null}">
    <a href="${returnURL}">返回上一功能页面</a> <br><br>
    </c:if>
    
    <a href="javascript:history.back();">后退</a><br><br>
    	  <a href="index.jsp">返回 ${appConfig.appTitle} 首页</a>
	  </TD>
      
    </TR>
 
  </TBODY>
</TABLE>


<!-- 底部页面开始 -->
<%@ include file="inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
