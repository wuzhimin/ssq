<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>


<TITLE>${appConfig.appTitle} - 查看供应商详细资料</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="../inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->
<p class="h1" align="center">查看供应商详细资料</p>
<TABLE  border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <font color=red size=5>${message}</font><br>
      </TD>
    </TR>
	<tr>
	<td width="50%">公司名:</td>
	<td width="50%">${vendor.name}</td>
	</tr>
	<tr>
      <td>详细地址:</td>
	  <td>${vendor.address}</td>
	  </tr>
	<tr>
      <td>分类:</td>
	  <td>${vendor.catalog}</td>
	  </tr>
	<tr>
      <td>网站地址:</td>
	  <td>${vendor.website}</td>
	  </tr>
	<tr>
      <td>公司简介:</td>
	  <td><pre>${vendor.note}</pre></td>
	  </tr>
	<tr>
      <td>公司标志图:</td>
	  <td><c:if test="${vendor.photo != null}"><a href=".${vendor.photo}" target="viewphoto" title="点击查看原始尺寸图片">
        <img border="0" src=".${vendor.photo}" width="64" height="64" >
        </a>
      </c:if>	  </tr>
		<tr>
		  <td>注册时间:</td>
		  <td>${vendor.regDate}</td>
    </tr>
	
  </TBODY>
</TABLE>

<!-- 底部页面开始 -->
<%@ include file="../inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
