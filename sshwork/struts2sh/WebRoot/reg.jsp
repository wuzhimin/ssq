<%@ page  pageEncoding="UTF-8"%>
<%-- 包含标签库 --%>
<%@ include file="inc_taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>

<TITLE>${appConfig.appTitle} - 注册</TITLE>

<!-- JS, CSS, BASE 标记等 -->
<%@ include file="inc_resources.jsp" %>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="inc_head.jsp" %>
<!-- 头部页面结束 -->
<!-- 页面主体 -->


<form action="user/reg.action" method="post" class='required-validate' enctype="multipart/form-data">
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <font color=green size=5>${message}</font><br>
      <span class="STYLE6">请输入您的个人信息后点击注册</span></TD>
    </TR>
	<tr>
	<td width="50%">*用户名:</td>
	<td width="50%"><input name="user.name" class="required min-length-5 max-length-20 validate-alphanum validate-ajax-user/ajaxValidate.action" value="beansoft">
	  (字母和数字的组合)</td>
	</tr>
	<tr>
	<td width="50%">*真实姓名:</td>
	<td width="50%"><input name="user.realName" type="text" class="required min-length-2 max-length-20 validate-ajax-user/ajaxValidate.action" id="real_name" value="刘长炯" ></td>
	</tr>
	<tr>
      <td>*密码:</td>
	  <td><input name="user.password" type="password" class="required min-length-6 max-length-20" value="123456" ></td>
	  </tr>
	<tr>
      <td>*密码(重复):</td>
	  <td><input name="password1" type="password" class="required equals-user.password" value="123456" ></td>
	  </tr>
	<tr>
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
	  <option value="0" selected>普通</option>
	  <option value="1">供应商</option>
	</select>	</td>
	</tr>
	<tr>
	<td width="50%">验证码: </td>
	<td width="50%"><input name="keycode" class="required validate-ajax-user/ajaxValidate.action"> <img id="regcodeImg" src="regcode.jsp"> <a href="javascript:void(0);"
onclick="document.getElementById('regcodeImg').src = 'regcode.jsp?d=' + new Date();"
>看不清楚?换一张</a></td>
	</tr>
		
	<tr>
	<td width="50%"><input type="submit" value="注册"></td>
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
<%@ include file="inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
