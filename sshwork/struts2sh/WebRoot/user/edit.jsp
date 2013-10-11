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
<!-- 头部页面开始 -->
<%@ include file="../inc_head.jsp" %>
<!-- 头部页面结束 -->
<!-- 页面主体 -->


<form action="user/update.action" method="post" class='required-validate' enctype="multipart/form-data">
<TABLE border=0 cellSpacing=0 cellPadding=0 width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <font color=green size=5>${message}</font><br>
      <span class="STYLE6">请输入新的个人信息后点击修改</span></TD>
    </TR>
	<tr>
	<td width="50%">*用户名:</td>
	<td width="50%">${loginedUser.name}</td>
	</tr>
	<tr>
	<td width="50%">*真实姓名:</td>
	<td width="50%">${loginedUser.realName}</td>
	</tr>
	<tr>
      <td>*详细地址:</td>
	  <td><input name="user.address" type="text" class="required max-length-200" id="address" value="${loginedUser.address}" ></td>
	  </tr>
	<tr>
      <td>*邮政编码:</td>
	  <td><input name="user.postCode" type="text" class="required validate-zip" id="post_code" value="${loginedUser.postCode}" ></td>
	  </tr>
	<tr>
      <td>家庭电话:</td>
	  <td><input name="user.homePhone" type="text" class="validate-phone" value="${loginedUser.homePhone}" ></td>
	  </tr>
	<tr>
      <td>移动电话:</td>
	  <td><input name="user.cellPhone" type="text" class="validate-mobile-phone" id="mobile_phone" value="${loginedUser.cellPhone}" ></td>
	  </tr>
	<tr>
      <td>办公电话:</td>
	  <td><input name="user.officePhone" type="text" class="validate-phone" id="office_phone" value="${loginedUser.officePhone}"></td>
	  </tr>
	<tr>
      <td>网站地址:</td>
	  <td><input name="user.website" type="text" class="validate-url  max-length-200" id="website" value="${loginedUser.website}" ></td>
	  </tr>
	<tr>
      <td>即时通信工具(QQ/MSN等):</td>
	  <td><input name="user.im" type="text" class="min-length-2 max-length-200" id="im" value="${loginedUser.im}" ></td>
	  </tr>
	<tr>
      <td>个人说明:</td>
	  <td><textarea name="user.note" cols="30" rows="4" class="max-length-100" id="note">${loginedUser.note}</textarea></td>
	  </tr>
	<tr>
      <td>新头像(如果不修改,请留空):</td>
	  <td><input name="photo" type="file" class="validate-file-png-jpg-gif" size="20">
	    (仅支持png,gif,jpg格式,大小建议64x64)</td>
	  </tr>

	<tr>
	<td width="50%"><input type="submit" value="修改资料"></td>
	<td width="50%">加星号(*)的内容必需填写才能顺利修改</td>
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
