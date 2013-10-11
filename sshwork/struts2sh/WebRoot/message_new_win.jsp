<%@ page  pageEncoding="UTF-8" %>
<!-- 消息显示页面,需要两个属性: title 和 message -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 我们使用 JSTL 来访问数据 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <base href="<%=basePath%>">


<TITLE>${appConfig.appTitle} - ${title}</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<!-- 表单验证 -->
<script src="easy_validation/lib/prototype.js" type="text/javascript"></script>
<script src="easy_validation/lib/effects.js" type="text/javascript"></script>
<script src="easy_validation/src/validation_cn.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="easy_validation/styles/style_min.css" />
		
<LINK rel=stylesheet 
type=text/css href="css/stylesheet.css">

<style type="text/css">
<!--
.STYLE6 {font-size: 12pt}
-->
</style>
</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center>
  <TBODY>
    <TR>
      <TD width=158><A href="./welcome.jsp"><IMG border=0 src="./images/supply_logo.png" width=158 height=73></A></TD>
      <TD width=159><IMG src="./images/synnex_header_images/synnex_header_bar2.gif" width=159 height=73></TD>
      <TD width=153><IMG src="./images/synnex_header_images/synnex_header_bar3.gif" width=153 height=73></TD>
      <TD width=158><IMG src="./images/synnex_header_images/synnex_header_bar4.gif" width=158 height=73></TD>
      <TD vAlign=top background=./images/synnex_header_images/synnex_header_bar5.gif width=132><TABLE border=0 cellSpacing=0 cellPadding=0 width=132 background=./images/synnex_header_images/synnex_header_bar5.gif height=73>
          <TBODY>
            <TR>
              <TD vAlign=center align=right><BR>
              <BR><a href="login.jsp"><img border=0 src="./images/login.gif" alt="进入系统"></a></TD>
            </TR>
          </TBODY>
      </TABLE></TD>
    </TR>
  </TBODY>
</TABLE>
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 background=./images/synnex_header_images/blue_bar.gif align=center height=20>
  <TBODY>
    <TR>
      <TD height=20 vAlign=center background=./images/synnex_header_images/blue_bar.gif width=760>&nbsp;&nbsp;&nbsp;&nbsp;<A class=headerbar href="/about_SYNNEX/about_SYNNEX.html">关于本系统</A>&nbsp;&nbsp;<FONT color=#ffffff face="Verdana, Arial, Helvetica, sans-serif">l</FONT>&nbsp;&nbsp;<A class=headerbar href="/news/news.html">新闻</A>&nbsp;&nbsp;<FONT color=#ffffff face="Verdana, Arial, Helvetica, sans-serif">l</FONT>&nbsp;&nbsp;<A class=headerbar href="/SYNNEX_central/SYNNEX_central.html">合作伙伴</A>&nbsp;&nbsp;<FONT color=#ffffff face="Verdana, Arial, Helvetica, sans-serif">l</FONT>&nbsp;&nbsp;<A class=headerbar href="/contact_us/contact_us.html">联系我们</A></TD>
    </TR>
  </TBODY>
</TABLE>
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 background=./images/synnex_header_images/lt_blue_bar.gif align=center height=20>
  <TBODY>
    <TR>
      <TD height=20 vAlign=center background=./images/synnex_header_images/lt_blue_bar.gif><a href="welcome.jsp">首页</a> &gt; ${title}</TD>
    </TR>
  </TBODY>
</TABLE>

<!-- 头部页面结束 -->
<!-- 页面主体 -->



<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD align="center">
	  <span class="STYLE6">${title}</span><br>
	  <font color=green size=5>${message}</font><br>
	  <a href="javascript:window.close()">关闭窗口</a>
	  </TD>
      
    </TR>
 
  </TBODY>
</TABLE>


<!-- 底部页面开始 -->
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 background=./images/synnex_header_images/blue_bar.gif align=center height=20>
  <TBODY>
    <TR>
      <TD height=20 align=middle><A class=footerbar href="javascript:void(0);">法律信息</A>&nbsp;&nbsp;&nbsp;&nbsp;<FONT color=#ffffff size=1>&gt;</FONT>&nbsp;&nbsp;&nbsp;&nbsp;<A class=footerbar href="javascript:void(0)">隐私条约</A>&nbsp;&nbsp;&nbsp;&nbsp;<FONT color=#ffffff size=1>&gt;</FONT>&nbsp;&nbsp;&nbsp;&nbsp;<A class=footerbar href="javascript:void(0)">使用条款</A>&nbsp;&nbsp;&nbsp;&nbsp;<FONT color=#ffffff size=1>&gt;</FONT>&nbsp;&nbsp;&nbsp;<A class=footerbar href="javascript:void(0)">联系我们</A></TD>
    </TR>
  </TBODY>
</TABLE>
<hr size=1 color=green width=760 align=center>
<center style="font-size:10pt">
  版权所有 2008 BeanSoft@126.com，保留所有权利。
</center>
<!-- 底部页面结束 -->
</BODY>
</HTML>
