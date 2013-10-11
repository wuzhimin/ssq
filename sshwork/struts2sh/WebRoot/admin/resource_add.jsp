<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>


<TITLE>${appConfig.appTitle} - 添加资源许可</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">

<p class="h1">管理 - 添加资源访问许可</p>

请输入新资源访问许可后点击添加<br>
<form action="admin/addResource.action" method="post" class='required-validate' >
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2" >
      <font color=green size=5>${message}</font><br>
      
      <font color=red>注意:</font> 默认情况下所有的.jsp和.action都可以被所有用户访问, 但只要一个URL配置了一个角色的资源访问许可, 
      那么其它的角色则自动不可再访问此URL地址, 我们采用的是白盒机制.
      
      </TD>
    </TR>
    
    <tr>
     <td>&nbsp;</td><td>&nbsp;</td>
    </tr>
   
	<tr>
	<td width="50%">*资源相对路径:<br>
	<FONT color="green">(说明: 此处可输入资源绝对路径精确地址, 如:<b>/reg.jsp</b>;如要允许访问子目录下内容, 请输入带通配符*的地址: <b>/路径/*</b>)
	</FONT>
	</td>
	<td width="50%"><input name="uri" class="required" value="/folder/*">
	</td>
	</tr>
	
    <tr>
     <td>&nbsp;</td><td>&nbsp;</td>
    </tr>
    
    	<tr>
	<td width="50%">资源描述(可选):<br>
	
	</td>
	<td width="50%"><input name="note">
	</td>
	</tr>
	

	<tr>
	<td width="50%">*允许访问此资源的角色:</td>
	<td width="50%"><select name="roleId" class="required">
	<c:forEach items="${roles}" var="role">
<option value='${role.id}'>${role.roleName}</option>
</c:forEach>

</td>
	</tr>

    <tr>
     <td>&nbsp;</td><td>&nbsp;</td>
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

<a href='admin/resourceList.action' title="浏览所有资源许可">
<img src='images/icons/list.gif' border=0 align='absmiddle'>浏览所有资源许可</a><br><br>


<a href='admin/role_add.jsp' title="添加新的用户角色">
<img src='images/icons/new.gif' border=0 align='absmiddle'>添加新角色</a><br><br>

<a href='admin/roleList.action' title="浏览所有角色">
<img src='images/icons/list.gif' border=0 align='absmiddle'>浏览所有角色</a><br><br>

</BODY>
</HTML>
