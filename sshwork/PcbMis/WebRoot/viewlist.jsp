<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'list.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<style>
table {
	border: 1px solid black;
	border-collapse: collapse;
}

table thead tr th {
	border: 1px solid black;
	padding: 3px;
	background-color: #cccccc;
}

table tbody tr td {
	border: 1px solid black;
	padding: 3px;
}
</style>
	</head>

	<body>
		<center>
			<h3>
				alarm管理：
			</h3>
			<br>
			<h4>
				<a href="../add.jsp">alarm注册</a>
			</h4>
			<s:form action="/pcb/queryViewList" theme="simple">
				<table>
					<thead>
						<tr>
							<th>
								选择
							</th>
							<th>
								编号
							</th>
							<th>
								WID
							</th>
							<th>
								id
							</th>
							<th>
								wcontent
							</th>
							<th>
								counts
							</th>
							<th>
								state
							</th>
							<th>
								uno
							</th>
							<th>
								wtime
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="viewList" status="u">
							<tr>
								<td>
									<input type="checkbox" name="id"
										value='<s:property value="id" />' />
								</td>
								<td>
									<%--<div id="<s:property value="%{u.index}"/> ">
								--%>


									<s:property value="#u.index+1" />


								</td>
								<td>
									<s:property value="id.wid" />
								</td>
								<td>
									<s:property value="id.id" />
								</td>
								<td>
									<s:property value="id.wcontent" />
								</td>
								<td>
									<s:property value="id.counts" />
								</td>
								<td>
									
								</td>
								<td>
									<s:property value="id.name" />
								</td>
								<td>
									<s:property value="id.pid" />
								</td>
								<td>
									<a
										href='<s:url action="queryAlarm"><s:param name="wid" value="wid" /></s:url>'>
										修改 </a> &nbsp;
									<a
										href='<s:url action="delete"><s:param name="wid" value="wid" /></s:url>'>
										删除 </a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<s:submit value="delete" />
			</s:form>
		</center>
	</body>
</html>
