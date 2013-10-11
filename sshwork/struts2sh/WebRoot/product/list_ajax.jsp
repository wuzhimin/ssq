<%@ page  pageEncoding="UTF-8" %>
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<!-- 页面主体 -->

<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="6"align="center">
      <font color=green size=5>${message}</font><br>
      <span class="STYLE6">${title}</span><br>
 共 ${pageBean.recordCount} 件商品 ${pageBean.pageCountHtml } 每页显示 ${pageBean.pageCount } 件
${pageBean.pageJumpLinkHtml }

      </TD>
    </TR>
	<tr bgcolor="gold" style="font-weight:bold">
	  <td>产品名</td>
	  <td>分类</td>
	  <td>供应商</td>
	  <td>原价</td>
	  <td>打折</td>
	  <td>库存</td>
    </tr>
 <c:forEach items="${products}" var="product" >
  <tr>
  <td><a href="product/findById.action?id=${product.id}" target="_blank">${product.name}</a>
    折扣价:${product.rebate*product.price}
    <c:if test="${product.photo != null}"><br><a href=".${product.photo}" target="viewphoto" title="点击查看原始尺寸图片">      <img border="0" src=".${product.photo}" width="64" height="64" >
        </a>      </c:if></td>
  <td>${product.catalog}</td>
  <td><a href="vendor/findById.action?id=${product.vendor.id}" target="_blank">${product.vendor.name}</a><br>所在地:${product.vendor.address}</td>
  <td>${product.price}元</td>
  <td>${product.rebate}</td>
  <td>${product.amount} 件 <c:if test="${product.amount > 0 && loginedUser.userType == 0}"><img src="./images/icart.gif" alt="添加到购物车" width="19" height="15" style="cursor:pointer"
  onClick="ajaxAddProduct(${product.id})"></c:if></td>
  </tr>
  </c:forEach>	 
  </TBODY>
</TABLE>

