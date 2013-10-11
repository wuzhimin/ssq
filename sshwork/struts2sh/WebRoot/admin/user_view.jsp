<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - 查看用户详细资料</TITLE>
<script>
function confirmDelete() {
	return confirm("数据删除后不可恢复, 您确定要继续?");
}
</script>
</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">


<!-- 页面主体 -->
<TABLE  border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
  <TBODY>
    <TR>
      <TD colspan="2"align="center">
      <h3>管理 - 查看用户详细资料</h3>
      <font color=red size=5>${message}</font><br>
      快速操作:
<c:if test="${user.userType != 4}"> 
  <a href='admin/deleteUser.action?id=${user.id}' title="删除此用户, 注意管理员用户不可删除" onclick="return confirmDelete()"><img src='images/icons/delete.gif' align='absmiddle' border=0>删除此用户</a></c:if>
  <a href='admin/toEditUser.action?id=${user.id}' title="修改用户详细信息" ><img src='images/icons/edit.gif' align='absmiddle' border=0>修改此用户</a>
      
      </TD>
    </TR>
	<tr>
	<td width="50%">用户名:</td>
	<td width="50%">${user.name}</td>
	</tr>
	<tr>
	<td width="50%">真实姓名:</td>
	<td width="50%">${user.realName}</td>
	</tr>
	<tr>
      <td>详细地址:</td>
	  <td>${user.address}</td>
	  </tr>
	<tr>
      <td>邮政编码</td>
	  <td>${user.postCode}</td>
	  </tr>
	<tr>
      <td>家庭电话:</td>
	  <td>${user.homePhone}</td>
	  </tr>
	<tr>
      <td>性别:</td>
	  <td><c:if test="${user.gender == 0}">男</c:if>
  <c:if test="${user.gender == 1}">女</c:if></td>
	</tr>
	<tr>
      <td>移动电话:</td>
	  <td>${user.cellPhone}</td>
	  </tr>
	<tr>
      <td>办公电话:</td>
	  <td>${user.officePhone}</td>
	  </tr>
	<tr>
      <td>生日:</td>
	  <td>${user.birthday}</td>
	  </tr>
	<tr>
      <td>电子邮件:</td>
	  <td>${user.email}</td>
	  </tr>
	<tr>
      <td>网站地址:</td>
	  <td>${user.website}</td>
	  </tr>
	<tr>
      <td>即时通信工具(QQ/MSN等):</td>
	  <td>${user.im}</td>
	  </tr>
	<tr>
      <td>个人说明:</td>
	  <td><pre>${user.note}</pre></td>
	  </tr>
	<tr>
      <td>头像:</td>
	  <td><c:if test="${user.photo != null}"><a href=".${user.photo}" target="viewphoto" title="点击查看原始尺寸图片">
        <img border="0" src=".${user.photo}" width="64" height="64" >
        </a>
      </c:if>	  </tr>
		<tr>
	<td width="50%">用户类型:</td>
	<td width="50%">
	<c:if test="${user.userType == 0}">普通</c:if>
  <c:if test="${user.userType == 1}">网上交易员</c:if>
  <c:if test="${user.userType == 2}">供应商</c:if>
  <c:if test="${user.userType == 3}">供应商</c:if>
  <c:if test="${user.userType == 4}">系统管理员</c:if>
  
  	</td>
	</tr>
		<tr>
		  <td>注册时间:</td>
		  <td>${user.regDate}</td>
    </tr>

		<tr>
		  <td>登录次数:</td>
		  <td>${user.loginCount}</td>
    </tr>
		<tr>
		  <td>购物总数:</td>
		  <td>${user.buyCount}</td>
    </tr>
	

		<tr>
		  <td>购物总支出:</td>
		  <td>${user.payNum}</td>
    </tr>
    
    		<tr>
		  <td>激活:</td>
		  <td>${user.active}</td>
    </tr>

  </TBODY>
</TABLE>


</BODY>
</HTML>
