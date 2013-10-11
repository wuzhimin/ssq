<%@ page language="java" contentType="text/html;charset=GB2312"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/product.tld" prefix="product" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html>
   <head>
   	<script language="javascript">
   		<!--
   		function save(){
   			
   		}
   		-->
   		</script>
   	  <link rel="stylesheet" href="<%=request.getContextPath()%>\css\laodong.css" type="text/css">
   </head>
   <body>
   	<html:form action="/doginfo.do?method=viewbzfile">
   		<div style="LINE-HEIGHT: 29px;" align=center><font size=2>查看狗登记信息</font><div>
   	 <table width="90%" align="center" cellpadding="1" cellspacing="1" class="tab-1" border="0">
   	 	 <tr class="tab-5">
   	 	 	  <td align="right" class="tab-3" width="10%">编号</td>
   	 	 	  <td><c:out value="${doginfoForm.obj.id}"/></td>
   	 	 </tr>
   	 	 <tr class="tab-5">
   	 	 	  <td align="right" class="tab-3" width="10%">狗名</td>
   	 	 	  <td><c:out value="${doginfoForm.obj.name}"/></td>
   	 	 </tr>
   	 	 <tr class="tab-5"> 	
   	 	 	 	<td align="center" colspan="2">
   	 	 	 		<input type="button" value="返回" onclick="javascript:history.go(-1);">
   	 	 	 		</td>
   	 	 </tr>
     </table> 
     	</html:form>
   </body>
</html>