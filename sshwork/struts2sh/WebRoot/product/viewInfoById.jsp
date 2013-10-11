<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - 查看商品详细资料</TITLE>

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
      <p class="h1">查看商品详细资料</p></TD>
    </TR>
	<tr>
	<td width="50%">商品名:</td>
	<td width="50%">${product.name}<br>
	  价格:<b><font color="red">${product.rebate*product.price}</font></b>元
	  <c:if test="${product.photo != null}"><br><a href=".${product.photo}" target="viewphoto" title="点击查看原始尺寸图片">
        <img border="0" src=".${product.photo}" width="64" height="64" >
      </a>      </c:if></td>
	</tr>
	<tr>
	  <td>销售情况:</td>
	  <td><DIV>售出 ${product.totalSold} 件，还剩 ${product.amount} 件 <c:if test="${product.amount > 0}"><img src="./images/icart.gif" alt="添加到购物车" width="19" height="15" style="cursor:pointer"
  onClick="ajaxAddProduct(${product.id})"></c:if></DIV></td>
    </tr>
	<tr>
      <td>定价:</td>
	  <td>${product.price}</td>
    </tr>
	<tr>
      <td>折扣:</td>
	  <td>${product.rebate}</td>
    </tr>
	<tr>
      <td>类别:</td>
	  <td>${product.catalog}</td>
    </tr>
	<tr>
      <td>制造商:</td>
	  <td><a href="vendor/findById.action?id=${product.vendor.id}" target="_blank">${product.vendor.name}</a></td>
    </tr>
	<tr>
      <td>商品描述信息:</td>
	  <td><pre>${product.description}</pre></td>
	  </tr>
		<tr>
		  <td>添加时间:</td>
		  <td>${product.addDate}</td>
    </tr>
  </TBODY>
</TABLE>


<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
