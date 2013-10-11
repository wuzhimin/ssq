<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!--<title>脑力劳动网---国内首家采用社会化网络的付费问答社区，为人们学习、工作和生活中的问题提供商业化问答平台</title>-->    
    <title>欢迎使用选项卡浏览</title>
    <!--
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/laodong.css" type="text/css">
    -->
 
    <meta http-equiv="keywords" content="在线问答,付费问答,即时支付,IT技术社区">
    <meta http-equiv="description" content="在线付费知识问答">
  
  <script type="text/javascript">
 //** iframe自动适应页面 **//

 //输入你希望根据页面高度自动调整高度的iframe的名称的列表
 //用逗号把每个iframe的ID分隔. 例如: ["myframe1", "myframe2"]，可以只有一个窗体，则不用逗号。
//正在装载数据…… 

 //定义iframe的ID
 var iframeids=["f1"]
 //如果用户的浏览器不支持iframe是否将iframe隐藏 yes 表示隐藏，no表示不隐藏
 var iframehide="yes"

 function dyniframesize() 
 {
  var dyniframe=new Array();
  
  for (i=0; i<iframeids.length; i++)
  {
   if (document.getElementById)
   {
    //自动调整iframe高度
    dyniframe[dyniframe.length] = document.getElementById(iframeids[i]);
    if (dyniframe[i] && !window.opera)
    {
     dyniframe[i].style.display="block"
     if (dyniframe[i].contentDocument && dyniframe[i].contentDocument.body.offsetHeight) //如果用户的浏览器是NetScape
      dyniframe[i].height = dyniframe[i].contentDocument.body.offsetHeight; 
     else if (dyniframe[i].Document && dyniframe[i].Document.body.scrollHeight){ //如果用户的浏览器是IE
      dyniframe[i].height = dyniframe[i].Document.body.scrollHeight; 
     }
     if(dyniframe[i].height <300){ 
     	     dyniframe[i].height  = 430;
      }
      var mytitle=dyniframe[i].Document.title
      if(mytitle.substring(0,9)=="NLLDZDYGD"){
      	dyniframe[i].height  = parseInt(mytitle.substring(9));
      }
    }
   }
   //根据设定的参数来处理不支持iframe的浏览器的显示问题
   if ((document.all || document.getElementById) && iframehide=="no")
   {
    var tempobj=document.all? document.all[iframeids[i]] : document.getElementById(iframeids[i])
    tempobj.style.display="block"
   }
  }
 }

 if (window.addEventListener)
 window.addEventListener("load", dyniframesize, false)
 else if (window.attachEvent)
 window.attachEvent("onload", dyniframesize)
 else
 window.onload=dyniframesize
</script>    
</head>

<body style="margin-top:0px">
       <iframe id="f" src="<%=request.getContextPath()%>/topic/yemei.jsp" name="topleft" width="100%" border="0" height="254"  frameborder=0 marginwidth=0 noresize scrolling=no>
       <iframe id="f1" onload="dyniframesize();" src="<%=request.getContextPath()%>/login.do?method=myindex" name="downmain" width="978" align="center" borer="0" frameborder=0 marginwidth=0 noresize scrolling=no> 
       <iframe src="<%=request.getContextPath()%>/topic/yejiao.jsp" name="yejiao" width="100%" align="center" border="0" height="70" frameborder=0 marginwidth=0 noresize scrolling=no>
</body>


</html>
