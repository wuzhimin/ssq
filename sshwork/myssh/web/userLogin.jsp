 
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for userLoginForm form</title>
	</head>
	<body>		
		<form action="userLogin.do">
         name:<INPUT type=text name=ce>
         <input type=submit>
        </form>
		ce:<%=request.getParameter("ce")%>
	</body>
	
</html>
