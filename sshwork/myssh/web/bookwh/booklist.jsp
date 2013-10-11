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
   		function add(){
   			document.bookmaForm.action="<%=request.getContextPath()%>/bookma.do?method=addbook"
   	    document.bookmaForm.submit();		
   		}
   		function view(id){
   			document.bookmaForm.action="<%=request.getContextPath()%>/bookma.do?method=viewbook&id=" + id;
   	    document.bookmaForm.submit();		
   		}
   		function upd(id){
   			document.bookmaForm.action="<%=request.getContextPath()%>/bookma.do?method=updbook&id=" + id;
   	    document.bookmaForm.submit();		
   		}
   		function bmbzwjdylist(){
   			document.officercForm.action="<%=request.getContextPath()%>/pubmodule/officerc.do?method=bmbzwjdylist";
   			document.officercForm.submit();	
   		}
   		//返回
	    function returna(){
        document.officercForm.action="<%=request.getContextPath()%>/branch/wzglzx.do?method=bmindex";
	      document.officercForm.submit();	
	    }
   		-->
   		</script>
   	  <link rel="stylesheet" href="<%=request.getContextPath()%>\css\laodong.css" type="text/css">
   </head>
   <body>
   	<html:form action="bookma.do?method=booklist">

   	</html:form>
   	<div style="LINE-HEIGHT: 29px;" align=center><font size=2>图书信息列表</font></div>
   	<% pageContext.include("/pub/util/ErrorInclude.jsp"); %>

   		<table align="center" border=0 width=95%><tr><td align=right>
        <input type="button" value="新增" onclick="add()"/>
      </td></tr></table>
   	 <table width="95%" align="center" cellpadding="1" cellspacing="1" class="tab-1" border="0">
   	 <tr class="tab-3">
    <td align="center">编号</td>
    <td align="center" width="35%">书名</td>
   	<td align="center" width="12%">操作</td>	 	
   	</tr>
   	<c:forEach var="item" items="${list}">
   	<tr class="tab-5">  
   	<td align="center"><c:out value="${item.id}"/></td>
    <td align="center"><c:out value="${item.name}"/></td>

    
   	  <td align="center">

          <input type="button" value="管理" onclick="upd('<bean:write name="item" property="id"/>')"/>
          <input type="button" value="查看" onclick="view('<bean:write name="item" property="id"/>')"/>
   	   </td>
   	  </tr>
   	 </c:forEach>
    </table>  
	
   </body>
</html>