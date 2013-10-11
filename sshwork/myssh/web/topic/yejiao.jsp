<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD 
id=Head1><TITLE>脑力劳动网首页</TITLE>
  <LINK href="csdn/style.css" type=text/css rel=stylesheet>
  <LINK href="csdn/public_header_footer2.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<SCRIPT language=javascript src="csdn/cmmenu.js" 
type=text/javascript></SCRIPT>

<SCRIPT language=javascript src="csdn/ExpertList.js" 
type=text/javascript></SCRIPT>

<SCRIPT language=JavaScript src="csdn/csdn_ggmm.js" 
type=text/javascript></SCRIPT>

<SCRIPT language=javascript type=text/javascript>
    <!--
    document.onclick = function(E)
    {
      E=event||E;  E=E.srcElement||E.target;
      if(E.tagName=="A" && E.href && E.getAttribute("rel")=="external")
      {
  	    window.open(E.href, "_blank");
  	    return false;
      }
    }
   
    function Search()
    {
        var a = document.createElement("A");
        a.target = "_blank";
        if(window.encodeURIComponent){
            a.href = "http://search.www.nlld.net/search/"+ encodeURIComponent(document.forms[0].key.value)+"/1/lt/";
        }else{
            a.href = "http://search.www.nlld.net/search/"+ (document.forms[0].key.value)+"/1/lt/";
        }
        document.body.appendChild(a);
        a.click();
        setTimeout(function(){a.parentNode.removeChild(a);}, 50)
    }
    function PostNewTopic()
    {
        document.forms["hideForm"].target = "_blank";
        document.forms["hideForm"].action = "IndexPage/NewTopic.aspx";
        document.forms["hideForm"].key.value = document.forms[0].key.value;
        document.forms["hideForm"].submit();
    }
    
    function Transfer()
    {
        if(window!=top && /indexframe\.aspx/i.test(top.location.href))
        {
            top.location.href = "index.htm";
            return false;
        }
        return true;
    }

    //-->
    </SCRIPT>

<BODY topmargin=0>
                <!--主题部分-->
<FORM id=form1 name=form1 action=index.aspx method=post>
<DIV><INPUT id=__VIEWSTATE type=hidden 
value=/wEPDwUKLTI3NDA3NDk4NWRkYN6lpojqrEwp7/8b8HfobvBksuM= name=__VIEWSTATE> 
</DIV>
<DIV id=CSDNPF>
<DIV class=CSDN-PHF>
<HR size=1>

<DIV>培训项目</DIV>
<HR size=1>

<SCRIPT type=text/javascript>/*<![CDATA[*/
    (function(){var a=document.getElementById("CSDNPF").getElementsByTagName("A");
    for(var i=0,n=a.length;i<n;i++)if(a[i].href&&a[i].rel=="mz")a[i].target="_blank";})();
    /*]]>*/</SCRIPT>

<SCRIPT src="csdn/counter.js" type=text/javascript></SCRIPT>
</DIV></DIV></BODY></HTML>
