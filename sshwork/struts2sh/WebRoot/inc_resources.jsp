<%-- 包含在页面的 head 标签里 --%>
<%@ page  pageEncoding="UTF-8"%>

<%--
BASE 标记处理, 解决相对路径问题.
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

<base href="<%=basePath%>" />
--%>

<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<META content="text/html; charset=UTF-8" http-equiv=Content-Type>

<!--  jquery, 注意加载顺序 -->
<script src="js/jquery-1.2.6.pack.js"></script>
<script type=”text/javascript”>
      var jQuery=$;
</script>

<!-- 表单验证 -->
<script src="easy_validation/lib/prototype.js" type="text/javascript"></script>
<script src="easy_validation/lib/effects.js" type="text/javascript"></script>
<script src="easy_validation/src/validation_cn.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="easy_validation/styles/style_min.css" />

<!-- 缩略图标 -->
<LINK rel="shortcut icon" type=image/x-icon href="images/favicon.ico">

<!-- 购物车js -->
<script src="js/cart.js" type="text/javascript"></script>

<!-- 样式表 -->
<LINK rel=stylesheet 
type=text/css href="css/stylesheet.css">

 <!-- Ext JS-->
    <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css" />
 	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="ext/ext-all.js"></script>
<!-- 公告  JS -->
    <script language="javascript" >
Ext.onReady(function(){
    var win;
    var button = Ext.get('show-btn');
    
    if(!button) {
    	return;
    }

    button.on('click', function(){
        // create the window on the first click and reuse on subsequent clicks
        if(!win){
            win = new Ext.Window({
                applyTo     : 'hello-win',
                layout      : 'fit',
                width       : 500,
                height      : 300,
                closeAction :'hide',
                plain       : true,
                items       : new Ext.TabPanel({
                    applyTo        : 'hello-tabs',
                    autoTabs       : true,
                    activeTab      : 0,
                    deferredRender : false,
                    border         : false
                }),

                buttons: [{
                    text     : '关闭',
                    handler  : function(){
                        win.hide();
                    }
                }]
            });
        }
        win.show(button);
    });
});    
    </script>