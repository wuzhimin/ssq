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
<TITLE>${appConfig.appTitle} - 资源访问许可列表</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">

<p class="h1">管理 - 资源访问许可列表</p>

<font color=green size=5>${message}</font>
<!-- 页面主体 -->
<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
	<tr bgcolor="gold" style="font-weight:bold">
	  <td>编号</td>
	  <td>地址</td>
	  <td>允许访问的角色</td>
	  <td>添加日期</td>
	  <td>说明</td>
	  <td>操作</td>
	 </tr>
 <c:forEach items="${resources}" var="resource" >
  <tr>
  <td>${resource.id}
  </td>
  <td><a href='${resource.uri}'>${resource.uri}</a></td>
  <td>
${resource.scmRole.roleName}
 </td>
  <td>${resource.addDate}</td>
  <td>${resource.note}</td>
  <td>  
<a href='admin/deleteByID.action?id=${resource.id}&entity=Resource' title="删除此资源许可" 
onclick="return confirmDelete()"><img src='images/icons/delete.gif' align='absmiddle' border=0>删除</a>
 </td>
  </tr>
  </c:forEach>	 
  
  </TBODY>
</TABLE>
在阅读此表时, 建议您先阅读下面的说明.<br>
资源访问检查的顺序是(以访问/admin/index.jsp为例):<br>
1. 检查是否存在允许任何角色访问/admin/index.jsp的记录 ==> 条件1;<br>
2. 检查是否存在允许任何角色访问/admin/*的记录 ==> 条件2;<br>
3. 如果 条件1 和 条件2 都不存在, 则允许任何角色访问此URL;<br>
4. 如果 条件1 或 条件2 之一存在, 那么只有允许的角色才可访问此URL, 进入5;<br>
5. 检查 是否存在允许<b>当前</b>角色访问/admin/index.jsp的记录, 是则运行访问, 否则进入下一步;<br>
6. 检查是否存在允许<b>当前</b>角色访问/admin/*的记录, 是则允许访问, 否则禁止用户访问并显示出错信息.

<h3>相关操作</h3>
<a href='admin/toAddResource.action' title="添加新的允许角色<br>访问的资源">
<img src='images/icons/new.gif' border=0 align='absmiddle'>添加新资源访问许可</a><br><br>

<a href='admin/role_add.jsp' title="添加新的用户角色">
<img src='images/icons/new.gif' border=0 align='absmiddle'>添加新角色</a><br><br>

<a href='admin/roleList.action' title="浏览所有角色">
<img src='images/icons/list.gif' border=0 align='absmiddle'>浏览所有角色</a><br><br>
</BODY>
</HTML>
