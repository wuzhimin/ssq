<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html> 
    <head> 
        <title>测试 分页</title> 
    </head> 
     
    <body> 
        <h1>测试 分页 page.jsp</h1> 
        <hr/> 
         
        <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6"> 
        <tr bgcolor="#EFF3F7"> 
          <TD align="center">ID</TD> 
          <TD align="center">内容</TD> 
          <TD align="center">时间</TD> 
          <TD align="center">相关操作</TD> 
            
        </tr> 
        <c:if test="${!empty pm.datas}"> 
          <c:forEach items="${pm.datas}" var="alarm"> 
            <tr bgcolor="#EFF3F7"> 
              <td align="center">${alarm.wid }</td> 
              <td align="center">${alarm.wcontent }</td> 
              <td align="center">${alarm.wtime}</td> 
              <td align="center"> 
                修改 
                     
                删除</td> 
            </tr> 
          </c:forEach> 
          </c:if> 
            <c:if test="${empty pm.datas}"> 
         <tr> 
            <td colspan="5" align="center" bgcolor="#EFF3F7"> 
            没有找到相应的记录 
            </td> 
         </tr> 
         </c:if> 
        </table> 
        <pg:pager url="pagerTaglib!pagerTaglib" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="3"> 
  <pg:first> 
    <a href="${pageUrl}">首页</a> 
  </pg:first> 
  <pg:prev> 
    <a href="${pageUrl }">上一页</a> 
  </pg:prev> 
  <pg:pages> 
    <c:choose> 
      <c:when test="${currentPageNumber eq pageNumber}"> 
        <font color="red">${pageNumber }</font> 
      </c:when> 
      <c:otherwise> 
        <a href="${pageUrl }">${pageNumber }</a> 
      </c:otherwise> 
    </c:choose> 
  </pg:pages> 
  <pg:next> 
    <a href="${pageUrl }">下一页</a> 
  </pg:next> 
  <pg:last> 
    <a href="${pageUrl }">尾页</a> 
  </pg:last> 
</pg:pager> 
    </body> 
    
</html> 
