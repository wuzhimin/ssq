<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %><HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - 添加商品</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="../inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->


<form action="product/add.action" method="post" class='required-validate' enctype="multipart/form-data">
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <font color=green size=5>${message}</font><br>
      <span class="STYLE6">请输入添加商品信息后点击添加, 只有审核后的商品才能进行销售.</span></TD>
    </TR>
    <tr>
      <td>*商品名:</td>
      <td><input name="product.name" class="required" id="product.name" value="Java学习光盘"></td>
    </tr>
	<tr>
	<td width="50%">*定价:</td>
	<td width="50%"><input name="product.price" class="required float-range-0.1-1000" id="vendor.name" value="50"></td>
	</tr>
	<tr>
      <td>*折扣:</td>
	  <td><input name="product.rebate" class="required float-range-0.1-1.0" value="1.0"></td>
	  </tr>
	<tr>
      <td>*分成:</td>
	  <td><input name="product.rate" class="required float-range-0.1-0.9" value="0.5"></td>
	  </tr>
	<tr>
      <td>*库存:</td>
	  <td><input name="product.amount" class="required int-range-0-1000" id="vendor.name" value="10"></td>
	  </tr>
	<tr>
      <td>*类别(可输入新类别):</td>
	  <td><input name="product.catalog" type="text" class="required max-length-20" id="post_code"  >
	    <select name="catalog_list" onChange="if(this.value) {this.form['product.catalog'].value = this.value}">
		  <option value="">--请选择现有类别</option>
	      <option value="硬件">硬件</option>
	      <option value="软件">软件</option>
	      <option value="书籍">书籍</option>
	      <option value="笔记本">笔记本</option>
                </select></td>
	  </tr>
	<tr>
      <td>*制造商:</td>
	  <td><select name="product.vendor.id" id="product.vendor.id" class="required ">
	  <c:forEach items="${vendors}" var="vendor" >
	  <option value="${vendor.id}">${vendor.name}</option>
	  </c:forEach>
                              </select></td>
	  </tr>
	<tr>
      <td>商品描述信息:</td>
	  <td><textarea name="product.description" cols="30" rows="4" class="max-length-100" id="note">小巧的笔记本</textarea></td>
	  </tr>
	<tr>
      <td>商品照片(仅供参考):</td>
	  <td><input name="photo" type="file" class="validate-file-png-jpg-gif" size="20">
	    (仅支持png,gif,jpg格式,大小建议64x64)</td>
	  </tr>	
	<tr>
	<td width="50%"><input type="submit" value="添加"></td>
	<td width="50%">加星号(*)的内容必需填写才能顺利添加</td>
	</tr>
	<tr>
	  <td>&nbsp;</td>
	  <td>&nbsp;</td>
	  </tr>	
  </TBODY>
</TABLE>
</form>

<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
