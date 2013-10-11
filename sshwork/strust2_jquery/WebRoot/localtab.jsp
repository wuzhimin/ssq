<%@page import="java.util.Map"%>
<%@page language="java" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjq" uri="/struts-jquery-grid-tags"%>


<html>
<head>
<title>Struts2 JQuery PlugIn Test</title>

<link  type="text/css"  href ="css/datepicker.css" rel="stylesheet"  /> 

<sj:head jqueryui="true" jquerytheme="sunny" locale="zh-CN" />

<s:if test="%{#parameters.theme_id==null}">
	<sj:head  jqueryui="true" jquerytheme="sunny" locale="zh-CN" />
</s:if>
<s:else>
	<sj:head jqueryui="true" jquerytheme="%{#parameters.theme_id}"
		locale="zh-CN" />
</s:else>
</head>
<body>

	<s:property value="myText" />
	<p>
		<s:url id='sport' action="remote" method="getSports"></s:url>
		<s:url id='car' action="remote" method="getCar"></s:url>
		<s:url id='news' action="remote" method="getNews"></s:url>

		<sj:tabbedpanel id="mytab1" title="my tabbedpanel" selectedTab="2"
			animate="true" spinner="正在加载。。。。">
			<sj:tab id="tab1" href="%{sport}" label="体育"></sj:tab>
			<sj:tab id="tab2" href="%{car}" label="汽车"></sj:tab>
			<sj:tab id="tab3" href="%{news}" label="新闻"></sj:tab>
			<sj:tab id="tab4" href="%{news}" label="女人" ></sj:tab>
		</sj:tabbedpanel>

		<sj:select id="dddd" list="[{'a1':'1'},{'a2':'2'}]"></sj:select>
	<div>
		生日：
		<sj:datepicker id="birthday" yearRange="1949:2040" changeYear="true"
			changeMonth="true">  我的
		</sj:datepicker>
		
		<p>
		<sj:dialog id="myDialog" autoOpen="false" title="test" modal="true" resizable="false" draggable="false">我的对话框</sj:dialog>
		<sj:a openDialog="myDialog" href="#"  cssClass ="buttonlink ui -state-default ui-corner-all">开窗</sj:a>
	</div>

	<p>
	<s:url id="ajaxTest" value="/AjaxTest.action" />
	<sj:a id="link1"  href="%{ajaxTest}" targets="div1">      Update Content    </sj:a>
	<p>
	<div>

		<a href="themes?theme_id=sunny">默认主题</a> <a
			href="themes?theme_id=flick">主题1</a> <a
			href="themes?theme_id=smoothness">主题2</a>

	</div>
	
	<s:url id='table_json_url' action="remote" method="getTableJson"></s:url>
	
	<sjq:grid
    	id="customers"
    	caption="Customer Examples"
    	dataType="json"
    	href="%{table_json_url}"
    	pager="false"
    	gridModel="customers"
    	rowList="10,15,30"
    	rowNum="15"
    	rownumbers="true"
    >
    	<sjq:gridColumn name="id" index="id" title="ID" formatter="integer" sortable="false"/>
    	<sjq:gridColumn name="name" index="name" title="Name" sortable="true"/>
    	<sjq:gridColumn name="country" index="country" title="Country" sortable="false"/>
    	<sjq:gridColumn name="city" index="city" title="City" sortable="false"/>
    	<sjq:gridColumn name="creditLimit" index="creditLimit" title="Credit Limit" formatter="currency" sortable="false"/>
    </sjq:grid>
	
	
</body>
</html>