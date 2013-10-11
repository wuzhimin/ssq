<%-- 头部包含页面 --%>
<%@ page pageEncoding="UTF-8"%>

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
				 &nbsp;&nbsp;<FONT color=#ffffff face="Verdana, Arial, Helvetica, sans-serif">l</FONT>&nbsp;&nbsp;<A class=headerbar href="javascript:void(0);" id="show-btn">查看公告:${appConfig.afficheTitle}</A>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<!-- 标题区 -->
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760
	background="./images/synnex_header_images/lt_blue_bar.gif" align=center
	height="20">
	<TBODY>
		<TR>
			<TD height=20 vAlign="center"
				background="./images/synnex_header_images/lt_blue_bar.gif">
				<a href="welcome.jsp">首页</a> &gt; ${title}
				<c:if test="${empty title}">
					<script>
				// 输出页面标题
				var t = document.title;
				if(t.indexOf("-") != -1) {
					t = t.substring(t.indexOf("-") + 2);
				}
				document.write(t);</script>
				</c:if>
			</TD>
		</TR>
	</TBODY>
</TABLE>

<!--  End of 标题区 -->

<!-- 公告显示区 -->
<div id="hello-win" class="x-hidden">
    <div class="x-window-header">系统公告</div>
    <div id="hello-tabs">
        <!-- Auto create tab 1 -->
        <div class="x-tab" title="${appConfig.afficheTitle}">
            ${appConfig.afficheContent}
        </div>

    </div>
</div>
<!-- End of 公告显示区 -->