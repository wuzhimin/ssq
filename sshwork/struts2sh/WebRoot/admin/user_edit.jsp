<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - 修改用户注册资料</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">


<!-- 页面主体 -->


<form action="admin/updateUser.action" method="post" class='required-validate'>
<!-- 编号 -->
<input type="hidden" name="user.id" value="${user.id}">

<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <p class="h1">管理 - 修改用户资料</p>
      <font color=green size=5>${message}</font><br>
      <span class="STYLE6">请输入新的个人信息后点击修改</span></TD>
    </TR>
	<tr>
	<td width="50%">*用户名:</td>
	<td width="50%">${user.name}</td>
	</tr>
	
	<tr>
	<td width="50%">*真实姓名:</td>
	<td width="50%"><input name="user.realName" type="text" class="required min-length-2 max-length-20 validate-ajax-user/ajaxValidate.action" id="real_name" value="${user.realName}" ></td>
	</tr>
	
		<tr>
      <td>设置密码(如果不需要修改用户密码, 请留空):</td>
	  <td><input name="password" type="password" class="min-length-6 max-length-20"></td>
	  </tr>
	<tr>
      <td>设置密码(重复):</td>
	  <td><input name="password1" type="password" class="equals-user.password" ></td>
	  </tr>
	<tr>
		
      <td>*详细地址:</td>
	  <td><input name="user.address" type="text" class="required max-length-200" id="address" value="${user.address}" ></td>
	  </tr>
	<tr>
      <td>*邮政编码:</td>
	  <td><input name="user.postCode" type="text" class="required validate-zip" id="post_code" value="${user.postCode}" ></td>
	  </tr>
	<tr>
      <td>家庭电话:</td>
	  <td><input name="user.homePhone" type="text" class="validate-phone" value="${user.homePhone}" ></td>
	  </tr>
	<tr>
      <td>移动电话:</td>
	  <td><input name="user.cellPhone" type="text" class="validate-mobile-phone" id="mobile_phone" value="${user.cellPhone}" ></td>
	  </tr>
	<tr>
      <td>办公电话:</td>
	  <td><input name="user.officePhone" type="text" class="validate-phone" id="office_phone" value="${user.officePhone}"></td>
	  </tr>
	<tr>
      <td>网站地址:</td>
	  <td><input name="user.website" type="text" class="validate-url  max-length-200" id="website" value="${user.website}" ></td>
	  </tr>
	<tr>
      <td>即时通信工具(QQ/MSN等):</td>
	  <td><input name="user.im" type="text" class="min-length-2 max-length-200" id="im" value="${user.im}" ></td>
	  </tr>
	<tr>
      <td>个人说明:</td>
	  <td><textarea name="user.note" cols="30" rows="4" class="max-length-100" id="note">${user.note}</textarea></td>
	  </tr>

	<tr>
	  <td>帐号启用:</td>
	  <td><select name="user.active"><option value="${user.active}">当前状态:${user.active}</option>
	  <option value="false">停用
	  </option>
	  <option value="true">启用</option>
	  </td>
	  </tr>	
	  
	<tr>
	<td width="50%"><input type="submit" value="修改资料"></td>
	<td width="50%">加星号(*)的内容必需填写才能顺利修改</td>
	</tr>

  </TBODY>
</TABLE>
</form>



</BODY>
</HTML>