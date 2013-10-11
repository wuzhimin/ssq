<%@ page  pageEncoding="UTF-8"%>
<%-- 包含标签库 --%>
<%@ include file="inc_taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
                    "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - 欢迎</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<!-- 缩略图标 -->
<LINK rel="shortcut icon" type=image/x-icon href="images/favicon.ico">
<LINK
rel=stylesheet type=text/css href="css/style.css">
<LINK
rel=stylesheet type=text/css href="css/pagestyle.css">
<LINK
rel=stylesheet type=text/css href="css/navmenu.css">
<LINK
rel=stylesheet type=text/css href="css/synnex-report.css">
<LINK
rel=stylesheet type=text/css href="css/calendarStyle.css">

  
<SCRIPT type=text/javascript src="js/common.js"></SCRIPT>
<SCRIPT type=text/javascript src="js/sniffer.js"></SCRIPT>
<SCRIPT type=text/javascript src="js/cookies.js"></SCRIPT>
<SCRIPT type=text/javascript src="js/expressTools.js"></SCRIPT>


</HEAD>
<BODY style="MARGIN: 0px">

<DIV><A name=top></A></DIV>
<DIV id=header>
  <DIV class=synnex_logo><A href="./index.jsp"><IMG border=0 src="./images/supply_logo.png" width=158 height=73></A><A href="http://www.synnex.com/index.jspl"></A></DIV>
  <TABLE id=header_table>
    <TBODY>
      <TR>
        <TD><SPAN>当前用户:</SPAN>
       
        
         </TD>
        <TD>${username} <br>编号: <A class=linkHoverWithBlue
      href="./user/findById.action?id=${loginedUser.id}">${loginedUser.id}</A> </TD>
        <TD>
        <!--  显示头像 -->
        <c:if test="${loginedUser.photo == null}">
        无头像
        </c:if>
        <c:if test="${loginedUser.photo != null}"><a href=".${loginedUser.photo}" target="viewphoto" title="点击查看原始尺寸图片">
        <img border="0" src=".${loginedUser.photo}" width="64" height="64" >
        </a>
        </c:if>        
        </TD>
      </TR>

    </TBODY>
  </TABLE>
</DIV>
<!-- 导航标签页 -->
<DIV id=tabs>
  <UL>
    <LI class=current><A
  href="welcome.jsp"><SPAN>${appConfig.appTitle}</SPAN></A> </LI>
    <LI class=blur><A
  href="index.jsp"><SPAN>站点首页</SPAN></A> </LI>
  </UL>
</DIV>

<!-- 导航菜单列表 -->
<DIV
style="Z-INDEX: 20; LEFT: 0px; POSITION: absolute; WIDTH: 100%; CLEAR: both; TOP: 70px; BACKGROUND-COLOR: #1f656f; "
id=menuWrapper>
  <DIV id=listmenu onmouseover=hideSelect() onmouseout=unhideSelect()>
    <UL>

      <LI><A href="javascript:void(0)">商品搜索</A>
        <UL>
          <LI><A href="product/search.htm">查找商品</A> </LI>
		  <LI><A href="product/search.htm"><b>打折商品</b></A> </LI>
          <LI><A class=xtools_link
    href="product/list.action">全部商品列表</A> </LI>
          <LI><A href="product/BrowseCategory.htm">分类浏览...</A> </LI>
          <LI><A
    href="product/BrowseManufacturer.do">按照供应商浏览...</A> </LI>
          <LI><A href="javascript:void(0)"><IMG
    class=arrow-right alt="submenu icon"
    src="welcome_files/header-menu-rght-arrow.gif"> <STRONG>快速查找 </STRONG></A>
            <UL>
              <LI><A
      href="">操作系统 </A> </LI>
              <LI><A
      href="">内存 </A> </LI>
              <LI><A
      href="">移动硬盘 </A> </LI>
              <LI><A
      href="">光驱 </A> </LI>
              <LI><A
      href="">办公用品</A> </LI>
              <LI><A
      href="">笔记本</A> </LI>
              <LI><A
      href="">配件 </A> </LI>
            </UL>
<LI><A href="product/"><b>销量排行</b></A> </LI>
        </UL>
      </LI>
      
        <c:if test="${loginedUser.userType==0}"><!-- 普通用户功能菜单 -->
      <LI><A
  href="javascript:void(0)">订单管理</A>
        <UL>
          <LI><A
    href="order/myOrderList.action">我的订单
            </A> </LI>
          <LI><A href="order/myBuyCount.action">购物总数</A> </LI>
          <LI><A
    href="order/myOrderList.action?payed=false">未支付订单</A> </LI>
          <LI><A
    href="order/myOrderList.action?payed=true">已支付订单</A> </LI>
        </UL>
      </LI>
      </c:if>
      
<!-- 供应商功能菜单 -->      
   <c:if test="${loginedUser.userType==1}">
      <LI><A href="javascript:void(0)">供应商</A>
        <UL>
          <LI><A
    href="product/toadd.action">提交商品
           </A> </LI>
          <LI><A
    href="">修改商品库存
           </A> </LI>
          <LI><A
    href="vendor/viewDenductSum.action"  target="_blank">查看分成
           </A> </LI>
          <LI><A
    href="vendor/mylist.action?user_id=${loginedUser.id}" target="_info">我的供应商列表</A> </LI>
          <LI><A
    href="vendor/add.jsp">添加供应商信息</A> </LI>
	 <LI><A
    href="vendor/list.action">浏览供应商列表</A> </LI>
        </UL>
      </LI>
   </c:if>
      
      
      <LI><A href="javascript:void(0)">您的账户</A>
        <UL>
          <LI><A href="user/changePassword.jsp">修改密码</A> </LI>
		   <LI><A href="user/myability.jsp">我的权限</A> </LI>
          <LI><A
    href="user/findById.action?id=${loginedUser.id}">帐户信息</A> </LI>
          <LI><A
    href="user/edit.jsp">修改注册资料
            </A> </LI>

          <LI><A
    href="user/list.action">浏览注册用户列表</A> </LI>

	<LI>
	<A
    href="logout.jsp"><b>注销</b></A> </LI>
        </UL>

      <LI><A href="javascript:void(0)"><b>控制面板</b></A>
        <UL>
          <LI><A
    href="admin/index.html">注册用户管理</A> </LI>
          <LI><A href="product/findListByAudited.action?audited=false">
            商品审核</A> </LI>

          <LI><A
    href="vendor/findListByAudited.action?audited=false">供应商审核
            </A> </LI>
			          <LI><A
    href="">商品分成调整
            </A> </LI>
			          <LI><A
    href="">商品打折调整
            </A> </LI>
        </UL>
      </LI>
      <LI style="COLOR: white">| </LI>
      <c:if test="${loginedUser.userType == 0}">      <LI><A href="cart/list.action" target="_cart"><IMG
  alt="cart icon" src="images/cart2.gif">购物车<b>[<span id="cartItemCount">${cart.itemCount + 0}</span>]</b>件商品</A></LI>
      <LI><A href="cart/createOrder.action">结算</A> </LI>
      </c:if>
      
      <LI><A href="logout.jsp"><b>注销</b></A> </LI>
    </UL>
  </DIV>
</DIV>

<!-- 快速通道 -->
<DIV style="Z-INDEX: 1; LEFT: 0px; POSITION: absolute; WIDTH: 180px; TOP: 84px"
id=xToolsTopBg><IMG
style="VERTICAL-ALIGN: text-top; WIDTH: 180px; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: 50px; BORDER-BOTTOM-STYLE: none"
alt="" src="welcome_files/etbg_top.gif"> </DIV>
<DIV style="Z-INDEX: 2; LEFT: 0px; POSITION: absolute; WIDTH: 180px; TOP: 84px"
id=xToolsTopText>
  <TABLE
style="WIDTH: 100%; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: none"
cellSpacing=0 cellPadding=0>
    <TBODY>
      <TR>
        <TD style="HEIGHT: 21px"></TD>
      </TR>
      <TR>
        <TD style="WIDTH: 24px">&nbsp;</TD>
        <TD style="VERTICAL-ALIGN: top; WIDTH: 120px; HEIGHT: 20px"><SPAN
      id=label_tool class=label_tools><I>快速通道</I></SPAN> </TD>
        <TD style="VERTICAL-ALIGN: bottom; WIDTH: 9px; HEIGHT: 9px"><A id=aXTools
      onclick="xToolsClick(); blur()"
      href="javascript:void(0);"><IMG
      style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 9px; HEIGHT: 9px; BORDER-RIGHT-WIDTH: 0px"
      id=img_express_tool name=img_express_tool
      alt="展开/折叠快速通道" src="welcome_files/expand.gif"></A> </TD>
        <TD style="WIDTH: 27px">&nbsp;</TD>
      </TR>
      <TR>
        <TD style="HEIGHT: 9px"></TD>
      </TR>
    </TBODY>
  </TABLE>
</DIV>
<DIV><BR>
</DIV>
<!-- 快速通道页面 -->
<DIV style="LEFT: 0px; POSITION: absolute; TOP: 144px" id=xToolsContent>
  <FIELDSET>
  <LEGEND class=xtools_legend><SPAN><A class=label_title_newet
href="http://ec.synnex.com/ecexpress/part/productSearch.html">商品查找</A></SPAN> </LEGEND>
  <DIV id=newProductSearch>
    <FORM name=product_search class='required-validate'
action="product/findByName.action" target="_blank">
      <input style="WIDTH: 102px" class='required xtools_input_text'
onFocus=this.select() name=keyword>
      <INPUT class=xtool_button value=Go type=submit name=directSearchSubmit>
        <BR>
        <INPUT value=true type=checkbox name=available>
      <SPAN
class=label_tool_comment>查找有库存商品</SPAN>
    </FORM>

    <A class=xtools_link
href="product/productSearch.do">高级查找
    </A> 
  </DIV>
  </FIELDSET>
  
  <FIELDSET>
  <LEGEND class=xtools_legend><SPAN><A class=label_title_newet
href="vendor/list.action" target="_blank">查找供应商</A></SPAN> </LEGEND>
  <DIV id=modelsKeyword>
    <FORM action="vendor/findListByName.action" class='required-validate' target="_blank">
      <DIV>
        <INPUT style="WIDTH: 102px" class='required xtools_input_text'
onfocus=this.select() name="vend_name" >
        <INPUT class=xtool_button value=Go type=submit>
      </DIV>
    </FORM>
    <SPAN class=label_tool_comment>(请输入供应商名字)</SPAN> </DIV>
  </FIELDSET>
  
  <FIELDSET>
  <LEGEND class=xtools_legend> <SPAN class=label_title_text>显示库存</SPAN> </LEGEND>
  <DIV id=productPnA>
  
   <FORM action="product/findByNameShowAmount.action"  class='required-validate'  target="_blank">
      <DIV>
        <INPUT style="WIDTH: 102px" class='xtools_input_text'
onfocus=this.select() name="keyword" >
        <INPUT class=xtool_button value=Go type=submit>
      </DIV>

     <SPAN
class=label_tool_comment>(请输入商品名称)</SPAN>
    </FORM>
  </DIV>
  </FIELDSET>
</DIV>
<!--快速通道工具栏结束-->

<DIV style="Z-INDEX: 5; LEFT: 175px; POSITION: absolute; TOP: 84px"
id=ettoplbg1>
  <TABLE class=bare cellSpacing=0 cellPadding=0>
    <TBODY>
      <TR class=bare>
        <TD style="WIDTH: 30px" class=bare>&nbsp;</TD>
        <TD style="WIDTH: 750px" class=bare><IMG
      style="MARGIN-LEFT: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: 50px; BORDER-BOTTOM-STYLE: none"
      alt="" src="welcome_files/h_center_bg.gif"> </TD>
        <TD style="WIDTH: 200px" class=bare>&nbsp;</TD>
      </TR>
    </TBODY>
  </TABLE>
</DIV>
<DIV
style="Z-INDEX: 6; LEFT: 175px; POSITION: absolute; WIDTH: 825px; TOP: 84px"
id=ettoplbg2>
  <TABLE
style="WIDTH: 100%; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: none"
class=bare cellSpacing=0 cellPadding=0>
    <TBODY>
      <TR class=bare>
        <TD style="WIDTH: 50px" class=bare><IMG
      style="MARGIN-LEFT: 0px; WIDTH: 50px; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: 50px; BORDER-BOTTOM-STYLE: none"
      alt="" src="welcome_files/h_left_bg.gif"> </TD>
        <TD style="WIDTH: 525px" class=bare><IMG
      style="MARGIN-LEFT: 0px; WIDTH: 100%; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; HEIGHT: 50px; BORDER-BOTTOM-STYLE: none"
      alt="" src="welcome_files/h_center_bg.gif"> </TD>
        <TD style="WIDTH: 250px" class=bare><IMG
      style="WIDTH: 250px; MARGIN-RIGHT: 0px; HEIGHT: 50px; bordre: none" alt=""
      src="welcome_files/h_right_bg.gif"> </TD>
      </TR>
    </TBODY>
  </TABLE>
</DIV>
<DIV
style="Z-INDEX: 7; LEFT: 786px; POSITION: absolute; WIDTH: 210px; TOP: 96px"
id=icon>
  <TABLE
style="WIDTH: 100%; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: none"
cellSpacing=0 cellPadding=0>
    <TBODY>
      <TR>
        <TD style="WIDTH: 10px">&nbsp;</TD>
        <TD style="VERTICAL-ALIGN: top; WHITE-SPACE: nowrap">&nbsp;&nbsp; </TD>
        <TD style="VERTICAL-ALIGN: top; WHITE-SPACE: nowrap">&nbsp;</TD>
        <TD
    style="VERTICAL-ALIGN: top; WIDTH: 176px; WHITE-SPACE: nowrap; TEXT-ALIGN: right"><TABLE
      style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 100%; BORDER-RIGHT-WIDTH: 0px"
      cellSpacing=0 cellPadding=0>
            <TBODY>
              <TR>
                <TD width="20" style="HEIGHT: 9px"></TD>
              </TR>
              <TR>
                <TD
            style="VERTICAL-ALIGN: bottom; WIDTH: 20px; TEXT-ALIGN: center"><A
            class=icon
            href="product/productSearch.html"><IMG
            style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 17px; HEIGHT: 17px; BORDER-RIGHT-WIDTH: 0px"
            id=img_cart0 name=img_cart alt="产品搜索"
            src="welcome_files/ifind.gif"></A> </TD>
            
            <c:if test="${loginedUser.userType == 0}">
                <TD width="20" style="WIDTH: 20px">&nbsp;</TD>
                <TD width="39"
            style="VERTICAL-ALIGN: bottom; WIDTH: 20px; TEXT-ALIGN: center"><A
            class=icon target="_cart"
            href="cart/list.action"><IMG
            src="images/icart.gif" alt="我的购物车" name=img_cart width="19" height="15"
            id=img_cart1
            style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 19px; HEIGHT: 15px; BORDER-RIGHT-WIDTH: 0px"></A> </TD>
            </c:if>    
                <TD width="20" style="WIDTH: 20px">&nbsp;</TD>
                <TD width="20"
            style="VERTICAL-ALIGN: bottom; WIDTH: 20px; TEXT-ALIGN: center"><A
            class=icon
            href="help/userGuide.html"
            ><IMG
            style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 16px; HEIGHT: 16px; BORDER-RIGHT-WIDTH: 0px"
            id=img_help0 name=img_help alt=帮助
            src="welcome_files/help.gif"></A> </TD>
                <TD width="36"
style="WIDTH: 36px">&nbsp;</TD>
              </TR>
            </TBODY>
          </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>
</DIV>

<!-- 正文内容,这里请显示相关的主体页面内容 -->
<DIV style="Z-INDEX: 8; LEFT: 180px; POSITION: absolute; WIDTH: 80%; TOP: 144px"
id=content>
<TABLE
style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 100%; BORDER-RIGHT-WIDTH: 0px"
cellSpacing=0 cellPadding=0>
  <TBODY>
  <TR>
    <TD>

<!-- 页面内容 -->
	<div id="contents"></div>

  <script>
  // 异步加载产品列表信息
  jQuery(document).ready(function(){
    jQuery("#contents").load("product/list.action?ajax=true&timestamp=" + new Date().getTime());
  });
  </script>
  

    </TD></TR>
  <TR>
    <TD>&nbsp;</TD></TR>
  <TR>
    <TD>&nbsp;</TD></TR>

	<!-- 版权信息 -->
  <TR>
    <TD style="WHITE-SPACE: nowrap; TEXT-ALIGN: center"
      class=footer><A class=footerbar href="javascript:void(0);">法律信息</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<A class=footerbar href="javascript:void(0)">隐私条约</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<A class=footerbar href="javascript:void(0)">使用条款</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<A class=footerbar href="javascript:void(0)">联系我们</A>
	  <hr size="1" color="green" width="760" align="center">
<center style="font-size:10pt">
  ${appConfig.copyright}
</center>
<!-- 底部页面结束 -->


	   </TD></TR></TBODY></TABLE>
	   
</DIV>

<script>
// 慢速更新购物车物品数
ajaxSlowUpdateCartItemCount();
</script>
</BODY>
</HTML>