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

<!-- 页面主体 -->


<form action="vendor/update.action" method="post" class='required-validate' enctype="multipart/form-data">
<input type="hidden" name="vendor.id" value="${vendor.id}" >

<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <font color=green size=5>${message}</font><br>
      <span class="STYLE6">请修改供应商信息后点击修改.</span></TD>
    </TR>
	<tr>
	<td width="50%">*公司名:</td>
	<td width="50%">${vendor.name}</td>
	</tr>
	<tr>
      <td>*详细地址:</td>
	  <td><input name="vendor.address" type="text" class="required max-length-200" id="address" value="${vendor.address}" ></td>
	  </tr>
	<tr>
      <td>*分类(可输入新分类):</td>
	  <td><input name="vendor.catalog" type="text" class="required max-length-20" id="post_code" value="${vendor.catalog}"  >
	    <select name="catalog_list" onChange="if(this.value) {this.form['vendor.catalog'].value = this.value}">
		  <option value="">--请选择现有分类</option>
	      <option value="硬件厂商">硬件厂商</option>
	      <option value="软件厂商">软件厂商</option>
	      <option value="出版">出版</option>
	      <option value="印刷">印刷</option>
          </select></td>
	  </tr>
	<tr>
      <td>网站地址:</td>
	  <td><input name="vendor.website" type="text" class="validate-url  max-length-200" id="website" value="${vendor.website}" ></td>
	  </tr>
	<tr>
      <td>公司简介:</td>
	  <td><textarea name="vendor.note" cols="30" rows="4" class="max-length-100" id="note">${vendor.note}</textarea></td>
	  </tr>
	<tr>
      <td>公司标志图(如果不修改,请留空):</td>
	  <td><input name="photo" type="file" class="validate-file-png-jpg-gif" size="20">
	    (仅支持png,gif,jpg格式,大小建议64x64)</td>
	  </tr>	
	<tr>
	<td width="50%"><input type="submit" value="修改"></td>
	<td width="50%">加星号(*)的内容必需填写才能顺利注册</td>
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
