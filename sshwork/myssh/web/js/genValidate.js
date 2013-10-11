function isDigit (c)
{   
	return ((c >= "0") && (c <= "9"))
}
//判断输入参数s是否为整数
function isInteger (s)
{
    var i;
    if (s==""){
       return false;
    }
    for (i = 0; i < s.length; i++)
    {
        var c = s.charAt(i);
        if (!isDigit(c)) return false;
    }
    return true;
}   
//判断是否为非负整数
function isFfzs(s){
	 if(!isInteger (s)){
	 	  return false;
	 }
	 else
	 {
	 		if(s<0)
	 		   return false;
	 		else
	 			 return true;
	 }
}
//根据传入radio对象数组，获取选中对象的value值
function getRadioValue(obj){
	for(i=0;i<obj.length;i++){
		if(obj[i].checked)
		   return obj[i].value
	}
}
//判断是否yyyymmdd标准日期格式
function isdate(obj){
   			var mydate = obj.value;
   			if(mydate=="")
   			   return;
   			if(!isBzDate(mydate))
        {
   				 alert("日期格式(YYYYMMDD)不对！");
   				 obj.value="";
   				 obj.focus();
   			}
}
//判断是否yyyymmdd标准日期格式
function isBzDate(indate){
	if(!isInteger(indate))
	   return false;
	if(indate.length!=8)
	   return false;
	var month = indate.substring(4,6);

	if(month<=0||month>12)
	   return false;
	var day = indate.substring(6,8);

	if(day<=0||day>31)
	   return false;
	   
	return true;
}  
//判断是否为数字
function f_check_number(s)
{
    if(isNaN(s))
    {
       return false;
    }
    else
    {
       return true;
    }
}  
//判断是否为非负数字
function isFfsz(s){
	 if(!f_check_number (s)){
	 	  return false;
	 }
	 else
	 {
	 		if(s<0)
	 		   return false;
	 		else
	 			 return true;
	 }
}