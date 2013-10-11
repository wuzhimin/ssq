
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>userLoginSuccess.jsp</title>
    
    
  </head>
  
  <body>
    This a struts page. <br>
    Hello 
    <%
       String str = (String)request.getAttribute("userName");
       String cse = request.getParameter("userName");
       String ce = request.getParameter("ce");
       out.print("Str: " + str);
       out.print("Cse: " + cse);
       out.print("ce: " + ce);
       String dog = request.getParameter("dog");
       out.print("Str: " + dog);
       String dog1 = (String)request.getAttribute("dog");
       out.print("Str1: " + dog1);
       %>
  </body>
</html>
