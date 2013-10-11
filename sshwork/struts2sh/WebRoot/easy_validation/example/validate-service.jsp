<%@page contentType="text/html; charset=UTF-8" %>
<%

String what = request.getParameter("what");
String value = request.getParameter("value");
System.out.println("what="+what+" value="+value);
//返回错误信息给客户端
if("email".equals(what) && "badqiu@gmail.com".equals(value)) {
	out.println("email exists: message from server");
}
%> 