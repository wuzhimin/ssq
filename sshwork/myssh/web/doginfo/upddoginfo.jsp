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
   			document.doginfoForm.action="<%=request.getContextPath()%>/doginfo.do?method=upddoginfosave";
   			document.doginfoForm.submit();	
   		}
   		function del(){
   			document.doginfoForm.action="<%=request.getContextPath()%>/doginfo.do?method=deldoginfo";
   			document.doginfoForm.submit();	
   		}
   		function pdqx(){

    	  var lxid = document.all("fileobj.filelx").value;
    		var murl="<%=request.getContextPath()%>/pubmodule/officerc.do?method=pdyhfilelxbxqx&id=" + lxid;
    		var str = httpGet(murl);
        if(str!="true"){
        	alert("你当前签到角色没有权限编写此类型文件！");
        	return false;
        }
        else
        	return true;
     }
    function httpGet(murl){	
    		var context="";
    		try{
    			var xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    			xmlhttp.open("post", murl, false);
    			xmlhttp.send();
    			context = xmlhttp.responseText;
    			return context
    		}catch(ee){
    			alert("错误：" + ee)
    		}
    	}	
   		-->
   		</script>
   	  <link rel="stylesheet" href="<%=request.getContextPath()%>\css\laodong.css" type="text/css">
   </head>
   <body>
   	<html:form action="/doginfo.do?method=updbzfile">
   		<div style="LINE-HEIGHT: 29px;" align=center><font size=2>修改狗登记信息</font><div>
   	 <html:hidden property="obj.id"/>
   	 <table width="90%" align="center" cellpadding="1" cellspacing="1" class="tab-1" border="0">
       <tr class="tab-5">
          <td align="right" class="tab-3" width="10%">编号</td>  <td>
          	<c:out value="${doginfoForm.obj.id}"/>
          		 </td>
       </tr>
       <tr class="tab-5">
          <td align="right" class="tab-3">狗名</td>  <td><html:text property="obj.name"/></td>
       </tr>
       <tr class="tab-5"> 	
   	 	 	 	<td align="center" colspan="2">
   	 	 	 		<input type="button" value="保存" onclick="save()">
   	 	 	 		<input type="button" value="删除" onclick="del()"></td>
   	 	 </tr>
     </table> 
     	</html:form> 	
   </body>
<html>