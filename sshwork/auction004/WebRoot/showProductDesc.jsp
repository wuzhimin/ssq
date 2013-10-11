<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showProductDesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="text-align: center">
   <h3>在线拍卖系统</h3>
   <s:form action="addBid" method="post">
   <s:hidden name="product.pid"/>
  <table border="1">
  
			<tr>
				<td>
					项目
				</td>
				<td>
					取值
				</td>
			</tr>
			<tr>
				<td>
					名字
				</td>
				
				<td><s:property value="product.pdesc"/></td>
			</tr>
			<tr>
				<td>
					描述
				</td>
				<td><s:property value="product.pdesc"/></td>
			</tr>
			<tr>
				<td>
					开始时间
				</td>
				<td><s:property value="product.pbegin_date"/></td>
			</tr>
			<tr>
				<td>
					结束时间
				</td>
				<td><s:property value="product.pend_date"/></td>
			</tr>
			<tr>
				<td>
					起拍价
				</td>
				<td><s:property value="product.pstart_price"/></td>
			</tr>
			<tr>
				<td>
					出价
					<s:textfield name="bid.bprice" theme="simple"></s:textfield>
				</td>
				<td>
					<s:submit value="竞拍" theme="simple"></s:submit>
				</td>
			</tr>
			<tr>
				<td>
					刷新
				</td>
				<td>
					放回列表
				</td>
			</tr>
		</table>
		</s:form>
		<br>
		<table border="1">
		
		  <tr>
				<td>
					竞拍时间
				</td>
				<td>
					竞拍价格
				</td>
				<td>
					竞拍人
				</td>
			</tr>
			<s:iterator value="product.getBid()">
			 <tr>
				<td>
					<s:property  value="bcreate_time" />
				</td>
				<td>
					<s:property  value="bprice" />
				</td>
				<td>
					<s:property  value="users.uname" />
				</td>
			</tr>
			</s:iterator>
		</table>
  </body>
</html>
