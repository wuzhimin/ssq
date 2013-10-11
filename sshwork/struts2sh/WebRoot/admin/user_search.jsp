<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>



<TITLE>${appConfig.appTitle} - 搜索用户</TITLE>

</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<p class="h1">管理 - 搜索用户</p>
<!-- 页面主体 -->

    <form action="admin/searchByUserName.action" class='required-validate' method="post">
    请输入用户名: <input name="user.name" class="required"><br>
    <input type="submit" value="搜索">    
    </form>
    

</BODY>
</HTML>