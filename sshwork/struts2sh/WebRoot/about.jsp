<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="inc_resources.jsp" %>

<!--  设置标题 -->
<c:set var="title" scope="request" value="关于" />

<TITLE>${appConfig.appTitle} - ${title}</TITLE>


</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<!-- 头部页面开始 -->
<%@ include file="inc_head.jsp" %>
<!-- 头部页面结束 -->

<!-- 页面主体 -->
<TABLE border="1"  cellpadding="0" style="border-collapse: collapse; " bordercolor="#000000" width=760 align=center >
<tr>
<td>
<h2>关于 ${appConfig.appTitle}</h2>
BeanSoft 是一家专门从事Java学习与研究的的北美上市公司, 旗舰产品屡获殊荣, 业界领先.<br>
公司始于1980年1月1日.<br>
&nbsp;&nbsp;&nbsp;&nbsp;BeanSoft 于 2003 年登陆北京，经过多年的发展，分别在海淀,
朝阳, 顺义三地成立了分公司，
作为其在中国地区的商务中心、全球业务支持中心、软件研发及技术支持中心、BPO/ITO外包中心，
为BeanSoft全球客户提供国际先进水平的企业商务解决方案，以及7×24小时的IT技术支持服务。
<br>
<img src='images/supply_logo.png'>
</td>
</tr>
</TABLE>

<!-- 底部页面开始 -->
<%@ include file="inc_foot.jsp" %>
<!-- 底部页面结束 -->
</BODY>
</HTML>
