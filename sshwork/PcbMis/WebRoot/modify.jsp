<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%> 
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <s:head />
		<sx:head />
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <s:form action="/pcb/update.action">
       <s:textfield label="counts" name="alarm.counts" value="%{alarm.counts}"></s:textfield>
       <s:textfield label="gaptime" name="alarm.gaptime" value="%{alarm.gaptime}"></s:textfield>
         <s:textfield label="state" name="alarm.state" value="%{alarm.state}"></s:textfield>
          <s:textfield label="uno" name="alarm.uno" value="%{alarm.uno}"></s:textfield>
         
          <s:hidden name="alarm.wid" value="%{alarm.wid}"></s:hidden>
           <s:textfield label="wcontent" name="alarm.wcontent" value="%{alarm.wcontent}"></s:textfield>
            <sx:datetimepicker name="alarm.wtime" id="wtime" displayFormat="yyyy-MM-dd" value="%{alarm.wtime}"></sx:datetimepicker>
       <s:submit name="update"></s:submit>
    </s:form>
  </body>
</html>
