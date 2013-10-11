<%@ page  pageEncoding="UTF-8"%>
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>

<TITLE>${appConfig.appTitle} - 添加新用户</TITLE>

<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">

<!-- 页面主体 -->
<p class="h1">管理 - 添加新用户</p>

<form action="admin/addUser.action" method="post" class='required-validate' enctype="multipart/form-data">
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <font color=green size=5>${message}</font><br>
      <span class="STYLE6">请输入新用户信息后点击添加</span></TD>
    </TR>
	<tr>
	<td width="50%">*用户名:</td>
	<td width="50%"><input name="user.name" class="required min-length-5 max-length-20 validate-alphanum validate-ajax-user/ajaxValidate.action" value="beansoft">
	  (字母和数字的组合)</td>
	</tr>
	<tr>
	<td width="50%">*真实姓名:</td>
	<td width="50%"><input name="user.realName" type="text" class="required min-length-2 max-length-20" id="real_name" value="刘长炯" ></td>
	</tr>
	<tr>
      <td>*密码:</td>
	  <td><input name="user.password" type="password" class="required min-length-6 max-length-20" value="123456" ></td>
	  </tr>

      <td>*详细地址:</td>
	  <td><input name="user.address" type="text" class="required max-length-200" id="address" value="北京海淀" ></td>
	  </tr>
	<tr>
      <td>*邮政编码:</td>
	  <td><input name="user.postCode" type="text" class="required validate-zip" id="post_code" value="100000" ></td>
	  </tr>
	<tr>
      <td>家庭电话:</td>
	  <td><input name="user.homePhone" type="text" class="validate-phone" value="12345678" ></td>
	  </tr>
	<tr>
      <td>性别:</td>
	  <td><label>
	    <select name="user.gender" id="gender">
	      <option value="0" selected>男</option>
	      <option value="1">女</option>
	      </select>
	  </label></td>
	</tr>
	<tr>
      <td>移动电话:</td>
	  <td><input name="user.cellPhone" type="text" class="validate-mobile-phone" id="mobile_phone" value="13812345678" ></td>
	  </tr>
	<tr>
      <td>办公电话:</td>
	  <td><input name="user.officePhone" type="text" class="validate-phone" id="office_phone" value="56784321"></td>
	  </tr>
	<tr>
      <td>*生日:</td>
	  <td><input name="user.birthday" type="text" class="required validate-date-yyyy-MM-dd" id="birthday" value="1980-01-01" >
	    (格式:yyyy-MM-dd,如1980-01/01)</td>
	  </tr>
	<tr>
      <td>*电子邮件:</td>
	  <td><input name="user.email" type="text" class="required validate-email validate-ajax-user/ajaxValidate.action" value="beansoftstudio@msn.com" />
	  请务必输入真实的邮箱地址.</td>
	  </tr>
	<tr>
      <td>网站地址:</td>
	  <td><input name="user.website" type="text" class="validate-url  max-length-200" id="website" value="http://beansoft.blogjava.net" ></td>
	  </tr>
	<tr>
      <td>即时通信工具(QQ/MSN等):</td>
	  <td><input name="user.im" type="text" class="min-length-2 max-length-200" id="im" value="不用QQ" ></td>
	  </tr>
	<tr>
      <td>个人说明:</td>
	  <td><textarea name="user.note" cols="30" rows="4" class="max-length-100" id="note">SCM系统用户</textarea></td>
	  </tr>
	<tr>
      <td>头像:</td>
	  <td><input name="photo" type="file" class="validate-file-png-jpg-gif" size="20">
	    (仅支持png,gif,jpg格式,大小建议64x64)</td>
	  </tr>
		<tr>
	<td width="50%">用户类型:</td>
	<td width="50%"><select name="user.userType" id="user_type">
<option value="2">网上交易员</option>
<option value="3">财务经理</option>
	</select>	</td>
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


</BODY>
</HTML>
