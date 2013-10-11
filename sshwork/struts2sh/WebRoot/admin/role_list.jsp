<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<script>
function confirmDelete() {
	return confirm("数据删除后不可恢复, 您确定要继续?");
}
</script>
<TITLE>${appConfig.appTitle} - 用户角色列表</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<p class="h1">管理 - 用户角色列表</p>

<table border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width="80%">
<tr bgcolor="gold" style="font-weight:bold"><td><b>编号</b></td>
<td><b>角色名</b></td></tr>

<c:forEach items="${roles}" var="role">
<tr><td>${role.id}</td><td>${role.roleName}</td></tr>
</c:forEach>

</table>

<h3>相关操作</h3>
<a href='admin/role_add.jsp' title="添加新的用户角色">
<img src='images/icons/new.gif' border=0 align='absmiddle'>添加新角色</a><br><br>

<a href='admin/toAddResource.action' title="添加新的允许角色<br>访问的资源">
<img src='images/icons/new.gif' border=0 align='absmiddle'>添加新资源访问许可</a><br><br>



<a href='admin/resourceList.action' title="浏览所有的准许固定角色用户访问的资源">
<img src='images/icons/list.gif' border=0 align='absmiddle'>浏览所有资源访问许可</a><br><br>
</BODY>
</HTML>
