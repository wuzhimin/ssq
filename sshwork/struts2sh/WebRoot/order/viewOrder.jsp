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

<p class="h1" align="center">订单概况</p>


<!-- 页面主体 -->
<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
<tr style="font-weight:bold">
<td>编号</td>
<td>交易日期</td>
<td>金额</td>
<td>状态</td>
</tr>

<tr>

<td>${order.id}</td>
<td>${order.addDate}</td>
<td>${order.cost}</td>
<td><c:if test="${order.status == 0}">未支付</c:if>
<c:if test="${order.status == 1}">已支付</c:if>
</td>
</tr>

</TABLE>

<p class="h1" align="center">订单项详细清单</p>
<center>注: 定价和折扣信息以交易时为准.</center>

<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
<tr style="font-weight:bold">
<td>物品名</td>
<td>交易日期</td>
<td>数量</td>
<td>定价</td>
<td>折扣</td>
<td>金额</td>
</tr>

<c:forEach items="${order.orderItems}" var="item">
<tr>
<td><a href="product/findById.action?id=${item.product.id}" target="_blank">${item.product.name}</a>
    折扣价:${item.product.rebate * item.product.price}
    <c:if test="${item.product.photo != null}"><br><a href=".${item.product.photo}" target="viewphoto" title="点击查看原始尺寸图片">      <img border="0" src=".${item.product.photo}" width="64" height="64" >
        </a>      </c:if></td>
<td>${item.addDate}</td>
<td>${item.amount}</td>
<td>${item.price}元</td>
<td>${item.rebate}</td>
<td>${item.rebate * item.amount * item.price}</td>
</tr>
</c:forEach>
</TABLE>

<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
