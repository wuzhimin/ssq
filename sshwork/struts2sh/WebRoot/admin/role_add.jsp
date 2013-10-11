<%@ page  pageEncoding="UTF-8"%>
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>

<TITLE>${appConfig.appTitle} - 添加新用户角色</TITLE>

<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">

<!-- 页面主体 -->

<p class="h1">管理 - 添加新用户角色</p>
  
<form action="admin/addRole.action" method="post" class='required-validate' >
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <font color=green size=5>${message}</font><br>
      请输入新角色信息后点击添加</TD>
    </TR>
	<tr>
	<td width="50%">*角色名:</td>
	<td width="50%"><input name="roleName" class="required max-length-20 validate-ajax-user/ajaxValidate.action" value="新角色">
</td>
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
<h3>相关操作</h3>
<a href='admin/roleList.action' title="浏览所有角色">
<img src='images/icons/list.gif' border=0 align='absmiddle'>浏览所有角色</a><br><br>

<a href='admin/toAddResource.action' title="添加新的允许角色<br>访问的资源">
<img src='images/icons/new.gif' border=0 align='absmiddle'>添加新资源访问许可</a><br><br>


<a href='admin/resourceList.action' title="浏览所有的准许固定角色用户访问的资源">
<img src='images/icons/list.gif' border=0 align='absmiddle'>浏览所有资源访问许可</a><br><br>

</BODY>
</HTML>
