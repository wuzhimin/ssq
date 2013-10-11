<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<html>
	<head>
		<title>客户关系管理系统</title>
		<!-- 资源包 -->
		<link href="/JavaPrj_10/main.css" rel="stylesheet">
		<link href="/JavaPrj_10/ext/resources/css/ext-all.css" rel="stylesheet">
		<link rel='stylesheet' type='text/css'
			href='/JavaPrj_10/multisel/Multiselect.css'>
		<script type="text/javascript"
			src="/JavaPrj_10/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/ext/ext-all.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/ext/build/locale/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/multisel/Multiselect.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/multisel/DDView.js"></script>
		<!-- 当前用户和角色-->
		<script type="text/javascript">
		var currentUser="${sessionScope.userName}";
		var currentRole="${sessionScope.roleId}";
		</script>
		<SCRIPT Language="JavaScript">
		var msg="欢迎使用客户关系管理系统!";
		var interval = 400;
		seq = 0;
		function marquee() 
		{
		len = msg.length;
		window.status = msg.substring(0, seq+1);  //逐字增加文本
		seq++;
		if ( seq >= len ) { seq = 0 };   //如果文本已经显示完，则从头开始
		window.setTimeout("marquee();", interval);
		} 
		</SCRIPT>
	</head>

	<body onload="marquee()">
		<div id="copyright" class="x-hidden ">
			<p>
				当前用户：${sessionScope.userName}
				<c:choose>
					<c:when test="${sessionScope.roleId==1}">
						<font color="purple">(系统管理员)</font>
					</c:when>
					<c:when test="${sessionScope.roleId==2}">
						<font color="purple">(销售主管)</font>
					</c:when>
					<c:when test="${sessionScope.roleId==3}">
						<font color="purple">(客户经理)</font>
					</c:when>
					<c:otherwise>
						<font color="purple">(高管)</font>
					</c:otherwise>
				</c:choose>
			</p>
		</div>
		<div id="loading-mask" style=""></div>
		<div id="loading">
			<div style="text-align: center; padding-top: 25%">
				<img src="images/Loader.gif" width="32" height="32"
					style="margin-right: 8px;" align="absmiddle" />
				Loading......
			</div>
		</div>
		<div id="serviceCreatewin"
			style="position: absolute; top: 800px; left: 800px;"></div>
		<!-- 主面板 -->
		<script type="text/javascript" src="/JavaPrj_10/main.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/register.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/modifyPwd.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/style.js"></script>
		<!-- 营销管理 -->
		<script type="text/javascript" src="/JavaPrj_10/crm_js/sale/saleChance.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/sale/custDevelPlan.js"></script>
		<!-- 客户管理 -->
		<script type="text/javascript" src="/JavaPrj_10/crm_js/cust/custInfo.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/cust/custLinkman.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/cust/custActivity.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/crm_js/cust/custOrders.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/cust/custOrdersDetail.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/crm_js/cust/custLost.js"></script>
		<!-- 服务管理 -->
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/service/serviceCreate.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/service/serviceAllot.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/service/serviceDispose.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/service/serviceFeedback.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/service/servicePigeonhole.js"></script>
		<!-- 统计报表 -->
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/rept/custProffer.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/rept/custStructure.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/rept/custService.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/crm_js/rept/custLose.js"></script>
		<!-- 基础数据 -->
		<script type="text/javascript" src="/JavaPrj_10/crm_js/basd/dataManage.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/basd/selectProduct.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/basd/selectStorage.js"></script>
		<!-- 权限管理-->
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/right/userinfoUser.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/crm_js/right/role.js"></script>
		<script type="text/javascript"
			src="/JavaPrj_10/crm_js/right/rightManage.js"></script>
		<script type="text/javascript" src="/JavaPrj_10/crm_js/right/myRight.js"></script>
	</body>
</html>
