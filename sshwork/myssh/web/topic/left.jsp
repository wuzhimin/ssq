<%@ page language="java" contentType="text/html;charset=GB2312"%>
<html>
	<head>
		<style>   
  a{text-decoration:none}   
  </style>   

  <script>                     
  var   h=null   
  function   hit(path, obj){   
  if(!h)h=obj;   
  h.style.color='';   
  h=obj;   
  h.style.color='red'
  parent.maina.location.href=path;
  }   
  </script>
	</head>
	<form>
<a href="#" onclick="hit('<%=request.getContextPath()%>/login.jsp', this)">安全管理</a>
<br><br><a href="#" onclick="hit('<%=request.getContextPath()%>/bookma.do?method=booklist', this)">图书维护</a>
<br><br><a href="#" onclick="hit('<%=request.getContextPath()%>/doginfo.do?method=doginfolist', this)">狗登记信息维护</a>
<br><br>综合查询
</form>