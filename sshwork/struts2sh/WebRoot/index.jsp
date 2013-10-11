<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 我们使用 JSTL 来访问数据 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
<TITLE>${appConfig.appTitle}</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<!-- 缩略图标 -->
<LINK rel="shortcut icon" type=image/x-icon href="images/favicon.ico">
<LINK rel=stylesheet
type=text/css href="css/stylesheet.css">
<!-- 系统通用功能脚本 -- DreamWeaver -->
<SCRIPT language=JavaScript src="js/dreamweaver.js"></SCRIPT>
    
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
              <BR>								<%-- 登录链接 --%>
								<c:if test="${empty loginedUser}">
									<a href="login.jsp" target="_top"><img border=0 src="./images/login.gif"
											alt="进入系统"></a>
								</c:if>             &nbsp;&nbsp;</TD>
            </TR>
          </TBODY>
      </TABLE></TD>
    </TR>
  </TBODY>
</TABLE>
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760
	background=./images/synnex_header_images/blue_bar.gif align=center
	height=20>
	<TBODY>
		<TR>
			<TD height=20 vAlign="center"
				background=./images/synnex_header_images/blue_bar.gif width=760>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<A class=headerbar href="index.jsp">首页</A>&nbsp;&nbsp;
				<FONT color=#ffffff face="Arial">l</FONT>&nbsp;&nbsp;
				<A class=headerbar href="welcome.jsp"><font color="gold">商店</font></A>&nbsp;&nbsp;
				<FONT color=#ffffff face="Arial">l</FONT>&nbsp;&nbsp;
				<A class=headerbar href="parters.jsp">合作伙伴</A>&nbsp;&nbsp;
				<FONT color=#ffffff face="Arial">l</FONT>&nbsp;&nbsp;
				<A class=headerbar href="contact_us.jsp">联系我们</A>
				<c:if test="${loginedUser.userType == 0}"> 
				&nbsp;&nbsp;
				<FONT color=#ffffff face="Arial">l</FONT>&nbsp;&nbsp;
				<A href="cart/list.action" target="_cart" class=headerbar><IMG
							src="images/cart2.gif" alt="cart icon" border="0">购物车<b>[${cart.itemCount
							+ 0}]</b>件商品</A>&nbsp;&nbsp;<FONT color=#ffffff face="Arial">l</FONT>
				&nbsp;&nbsp;<A href="cart/createOrder.action" class=headerbar>结算</A>
				</c:if>
				
				<c:if test="${!empty loginedUser}">
				&nbsp;&nbsp;
				<FONT color=#ffffff face="Arial">l</FONT>&nbsp;&nbsp;
				<A href="logout.jsp" class=headerbar><b>注销</b>
				</A>
				</c:if>
				
			</TD>
		</TR>
	</TBODY>
</TABLE>
</TABLE>



<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 background=./images/synnex_header_images/lt_blue_bar.gif align=center height=20>
  <TBODY>
    <TR>
      <TD height=20 vAlign=center background=./images/synnex_header_images/lt_blue_bar.gif>          请选择地区：
            <SELECT class=menu  style="height:20" onChange="MM_jumpMenu('parent',this,0)" name=Menu>
            <OPTION value=index.jsp>
            中国大陆
            </OPTION>
            <OPTION value=index_en.jsp>
            美国
            </OPTION>



          </SELECT><INPUT onClick="MM_jumpMenuGo('Menu','parent',0)" style="height:20"  value=Go type=button name=Button1></TD>
    </TR>
  </TBODY>
</TABLE>
<!-- 头部页面结束 -->
<!-- 公司Logo或者Flash动画 -->
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD align="center" >
          <img src="images/homebg.jpg" width="760" height="400" align="middle" >
		<br>
		${appConfig.appTitle} 系统是一个基于浏览器瘦客户端和Java EE服务器技术的在线分销管理系统, 利用我们专业的销售和物流服务,为您提供方便, 给顾客和供应商提供双赢的平台.
		</TD>
    </TR>
  </TBODY>
</TABLE>
<!-- 底部页面开始 -->
<%@ include file="inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>