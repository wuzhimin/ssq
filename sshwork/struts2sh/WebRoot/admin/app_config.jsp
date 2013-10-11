<%@ page  pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%-- 包含标签库 --%>
<%@ include file="../inc_taglib.jsp" %>
<HTML>
<HEAD>
<!-- JS, CSS, BASE 标记等 -->
<%@ include file="../inc_resources.jsp" %>

<TITLE>${appConfig.appTitle} - 修改网站全局设置</TITLE>

<script type="text/javascript" src="./fckeditor/fckeditor.js"></script>
	<script type="text/javascript">

window.onload = function()
{

	var sBasePath = "./fckeditor/" ;// 指向 fckeditor的存放目录

	var oFCKeditor = new FCKeditor( 'appConfig.afficheContent' ) ;
	oFCKeditor.Height	= '200px' ;// 高度, 可以用 100%
	//oFCKeditor.Width	= '400px' ;// 宽度
	oFCKeditor.BasePath	= sBasePath ;// 设置基础路径
	oFCKeditor.Config['SkinPath'] = './skins/office2003/' ;// 换肤, 查看 fckeditor\editor\skins 下的皮肤目录名
	oFCKeditor.ToolbarSet = 'Default';// 切换工具条配置, 默认为完全, Basic是简洁模式, 可以用来发评论时
	//oFCKeditor.ToolbarSet = 'Basic';
	//oFCKeditor.Config['FullPage'] = true ;// 指定编辑的HTML代码是否包括<html>完整标记,可用来做内容管理时发布完整HTML页面
	oFCKeditor.ReplaceTextarea() ;// 替换文本区为可视化网页编辑器
	
}
	</script>
	
</HEAD>
<BODY leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">

<p class="h1">系统管理 - 修改网站全局设置</p>

在这里您可以修改和整个网站的显示相关的属性.

<form action="admin/updateAppConfig.action" method="post" class='required-validate'>
网站标题: <input name="appConfig.appTitle" value="${appConfig.appTitle}" size="40" class="required" /><br>
版权信息: <input name="appConfig.copyright" value="${appConfig.copyright}" size="80" class="required" /><br>
系统公告标题: <input name="appConfig.afficheTitle" value="${appConfig.afficheTitle}" class="required" /><br>

<FIELDSET>
<LEGEND>系统公告内容(支持HTML代码):</LEGEND>
<textarea name="appConfig.afficheContent" style="width:100%" rows=5 cols=80 class="required">${appConfig.afficheContent}</textarea>
</FIELDSET>

<FIELDSET>
<LEGEND>关键字过滤:</LEGEND>
多个关键字以换行分开, 如果有特殊符号请用\\转义,例如*应为:\\*<br>
<textarea name="appConfig.badwords" title="这些敏感词将会在用户注册时进行检查, 减少出现违法行为." style="width:100%" rows=10 cols=80 class="required">${appConfig.badwords}</textarea>
</FIELDSET>

<input type="submit" value="立即修改">
</form>

TODO 增加全站关闭功能?


</BODY>
</HTML>
