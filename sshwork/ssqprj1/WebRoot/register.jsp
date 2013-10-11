<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="true" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjq" uri="/struts-jquery-grid-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>注册帐户 - 会员中心</title>

<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.md5.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>

<script type="text/javascript" >
        $(document).ready(function(){
        
        	// 重置
            $("#registerform")[0].reset();
            
            
			// 添加用户名验证方法       
			jQuery.validator.addMethod("userIdCheck", function(value,
					element) {
				return this.optional(element)
						|| /^[a-z][a-z0-9_]{4,19}$/i.test(value);
			}, "只能包括英文字母、数字和下划线，首字符必须为字母");
			
			//通过id查找要进行校验的表单
			$("#registerform").validate({
					//设置校验触发的时机,默认全是true。不要尝试去设置它为true，可能会有js错误。
					onsubmit:true,
               		//onfocusout:false,

					
					// 验证通过后执行的动作
					success:function(label){
					    label.text("ok!").addClass("success");
					},
					
					// 手动设置错误信息的显示方式

					rules : {
						uname : {
							required : true,
							rangelength : [ 5, 30 ],
							userIdCheck:true,
							remote : { url:'checkUserName',
									   data: {                                         // 要传递的数据，默认已传递应用此规则的表单项
				                			uname: function() {
				                        		return $("#uname").val();
				                            }
				               		   }
									
								     }
						},

						 pwd1: {
						     required: true,
						     minlength: 5
						 },
						 
						 pwd2: {
						     required: true,
						     equalTo: "#pwd1"
						 },
						 
						 em: {
						     required : true,
							 email : true
						 },
						 
						 validateCode: {
						 	required : true,
							remote : { url:'checkValidateCode',
									   data: {                                         // 要传递的数据，默认已传递应用此规则的表单项
				                			clientValidateCode: function() {
				                        		return $("#validateCode").val();
				                            }
				               		   }
									
								     }
						 }
				   },

					//校验提示信息
					messages : {
						uname : {
							required : "请输入用户名",
							rangelength : "用户名长度必须为{0}到{1}个字符或汉字",
							remote: "此用户已被注册！"
						},

						pwd1: {
						    required: "请输入密码",
						    minlength: "密码的最小长度是{0}个字符"
						},

						pwd2: {
							required: "请再次输入密码",
						    equalTo: "请再次输入相同的密码！"
						}, 
						
						em: {
						     required : "请输入电子邮箱",
							 email : "请输入正确的电子邮箱"
						 },
						 
						validateCode: {
						     required : "请输入验证码",
							 remote : "请输入正确的验证码"
						 } 
					}
				});
			});
</script>
    <!-- js校验结束 -->

</head>

<body>
	<s:form id="registerform" action="user" method="post" theme="simple">
		<div class="login_cont01">
			<h5>帐户信息</h5>
			<div class="table">
				<table border="0" width="100%">
					<tr>
						<td width="150" class="right" valign="top"><dfn>*</dfn>用户名：</td>
						<td>
							<div class="oneline">
								<input type="text" id="uname" name="uname" class="inputbox" maxlength="50" />
							</div>
							<div class="twoline">（5-20位字母、数字或下划线组合，首字符必须为字母。）</div>  </td>
					</tr>
					<tr>
						<td width="150" class="right" valign="top"><dfn>*</dfn>密码：</td>
						<td>
							<div class="oneline">
								<input type="password" id="pwd1"  name="pwd1" class="inputbox" maxlength="50" />
								<input type="hidden" id="pwd"  name="pwd"/>
								
								<ul id="pwd-strong" style="display:none;">
									<li>弱</li>
									<li>中</li>
									<li>强</li>
								</ul>
							</div>
							<div class="clear"></div>
							<div class="twoline">
								（为了您的帐户安全，强烈建议您的密码使用字符+数字等多种不同类型的组合，并且密码长度大于5位。）</div></td>
					</tr>
					<tr>
						<td width="150" class="right" valign="top"><dfn>*</dfn>再次输入密码：</td>
						<td>
							<div class="oneline">
								<input type="password" id="pwd2" name="pwd2" class="inputbox" maxlength="50" />
							</div>
							<div class="twoline">（确保密码输入正确。）</div></td>
					</tr>
					<tr>
						<td width="150" class="right" valign="top"><dfn>*</dfn>E-mail：</td>
						<td>
							<div class="oneline">
								<input type="text" id="em" name="em" class="inputbox" maxlength="100" />
							</div>
							<div class="twoline">（激活码将会发到您的邮箱中，只有激活帐户才可以使用！）</div></td>
					</tr>

					<tr>
						<td>验证码：</td>

						<td>
							<input type="text" id="validateCode" name="validateCode"><div class="oneline">
								<img onclick="reloadValidate(this);"
									src="generateValidateCode?<%=Math.random()%>>">
							</div>
							</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="login_cont10">
			<div class="table">
				<table border="0">
					<tr>
						<td width="150" class="right" valign="top"></td>
						
						<td><s:submit name="submit"  value="注册" method="register" onclick="md5pwd();" ></s:submit> 
						<input type="button" name="login" onclick="window.location.href='login.jsp'" value="登录" />
						</td>
					</tr>
					<tr>
						<td width="150" class="right" valign="top"></td>
						<td><span id="sp_err" style="color:#ff0000;"><s:property value="#txtinfo"/></span></td>
					</tr>
				</table>
			</div>
		</div>
	</s:form>
	<script type="text/javascript">
		function tijiao() {
			$("#registerform").submit();
		}
		
		function md5pwd() {
		    // 把密码加密
		    if($("#pwd1").val()==null || $("#pwd1").val()=="") {
		    	$("#pwd").val("");
		    } else {
				$("#pwd").val($.md5($("#pwd1").val()));
			}
		}
		
		function reloadValidate(image){  
			image.src = "generateValidateCode?"+Math.random(); 
			$("#validateCode").val("");
		}  
		
	</script>
</body>
</html>
