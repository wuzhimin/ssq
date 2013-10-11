<%@ page language="java" contentType="text/html;charset=GB2312"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/product.tld" prefix="product" %>
<html>
   <head>
   	<script language="javascript">
   		<!--
   		function save(){
   			document.doginfoForm.action="<%=request.getContextPath()%>/doginfo.do?method=adddoginfosave";
   			document.doginfoForm.submit();	
   		}
   		function view(){
   			document.doginfoForm.action="<%=request.getContextPath()%>/pubmodule/officerc.do?method=bmbzwjdylist";
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
   	<html:form action="/doginfo.do?method=adddoginfo">

   	<div style="LINE-HEIGHT: 29px;" align=center><font size=2>狗登记书本</font><div>
   	 <table width="90%" align="center" cellpadding="1" cellspacing="1" class="tab-1" border="0">
       <tr class="tab-5">
          <td align="right" class="tab-3">狗名</td>  <td><html:text size="46" property="obj.name"/></td>
          <td align="right" class="tab-3">主人名</td>  <td><html:text size="46" property="obj.master"/></td>
       </tr>
       <tr class="tab-5">
          <td align="right" class="tab-3">狗龄</td>  <td><html:text size="46" property="obj.age"/></td>
          <td align="right" class="tab-3">狗的描述</td>  <td><html:text size="46" property="obj.ms"/></td>
       </tr>
       <tr class="tab-5"> 	
   	 	 	 	<td align="center" colspan="4">
   	 	 	 		<input type="button" value="保存" onclick="save()">
   	 	 	 		</td>
   	 	 </tr>
     </table> 
     	</html:form> 	
   </body>
</html>