<%@ page  pageEncoding="UTF-8" %>
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<script>

// AJAX 调用清空购物车的方法
function emptyCart() {
	if(confirm("您确定要购物车清空嘛?")) {
		jQuery.get("cart/empty.action" +
		"?timestamp=" + new Date().getTime(), function(data){
			location.reload();
		}); 
	}	
}

// AJAX 调用后台进行购物车移出商品操作
function removeOutOfCart(product_id) {
	if(confirm("您确定要将该商品移出购物车?")) {
		jQuery.get("cart/ajaxRemoeOutProduct.action?product_id=" + product_id +
		"&timestamp=" + new Date().getTime(), function(data){
  			alert("从购物车中移出成功!" + data);
			location.reload();
		}); 
	}
}

// 使用AJAX方式从后台更新商品购买数量
function ajaxChangeCount(product_id, input) {
	// 判断是否修改了数量
		if(input.value == input.oldValue) {
			return;
		}
	// 是否超出了库存, 转换数据类型避免出错
		if(parseInt(input.value) > parseInt(input.maxCount)) {
			input.value = input.oldValue;
			return;
		}
	// 设置为0等价于删除操作	
		if(input.value == 0) {
			removeOutOfCart(product_id);
			return;
		}
		
		// 异步执行更改购买数量操作
		jQuery.get("cart/ajaxChangeAmout.action?product_id=" + product_id + "&amount=" + input.value +
		"&timestamp=" + new Date().getTime(), function(data){
			if(data) {
				alert("商品数量更改失败:" + data);
				input.value = input.oldValue;
			} else {
				// 更新原始值
				//input.oldValue = input.value;
				location.reload();
			}
		});
}

</script>
</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="../inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->

<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="6"align="center">
	  <img src="./images/car_logo.gif" align="absmiddle">购物车帮您一次性完成批量购买与付款，下单更快捷，付款更简单！<img src="./images/car_emtpy.gif" alt="清空购物车" width="95" height="20" onClick="emptyCart()" style="cursor:pointer"><br>
      <font color=green size=5>${message}</font><br>
      <span class="STYLE6"><br>
	  ${title}</span></TD>
    </TR>
	<tr bgcolor="gold" style="font-weight:bold">
	  <td colspan="2">购物车上的商品 </td>
	  <td width="110">购买数量</td>
	  <td width="163">商品库存</td>
    </tr>
 <c:forEach items="${orders}" var="item" >
  <tr>
  <td width="158"><a href="product/findById.action?id=${item.product.id}">${item.product.name}</a>
    <c:if test="${item.product.photo != null}"><br><a href=".${item.product.photo}" target="viewphoto" title="点击查看原始尺寸图片">      <img border="0" src=".${item.product.photo}" width="64" height="64" >
        </a>      </c:if></td>
  <td width="309">供应商: <a href="vendor/findById.action?id=${item.product.vendor.id}" target="_blank">${item.product.vendor.name}</a><br>
    价格:<b><font color="red">${item.product.rebate*item.product.price}</font></b>元<br>
    <img src="./images/cart_removeout.gif" alt="将产品移出购物车" style="cursor:pointer" onClick="removeOutOfCart(${item.product.id})" width="74" height="21"></td>
  <td><form class='required-validate' onSubmit="ajaxChangeCount(${item.product.id}, this.count); return false;"><input name="count" value="${item.amount}" size="5" oldValue="${item.amount}" maxCount="${item.product.amount}" class="required int-range-0-${item.product.amount}"><input type="submit" value="修改"></form></td>
  <td>${item.product.amount}</td>
  </tr>
  </c:forEach>
  <tr><td colspan="2">商品合计：<b><font color="red">${cart.cost}</font></b> 元 </td></tr>
  <tr><td colspan="2" align="center">如果您已经完成挑选，请您确认订单并结算。<br>      <a href="cart/createOrder.action"><img src="./images/paynow.gif" alt="现在付款" width="103" height="27" border="0"></a><br></td>
  <td colspan="2" align="center">您也可以返回到最近加入的商品列表，继续挑选商品。<br>
    <a href="javascript:window.close();"><img src="./images/findAgain.gif" alt="关闭购物车,继续挑选" width="115" height="28" border="0" ></a></td>
  </tr>
  </TBODY>
</TABLE>


<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
