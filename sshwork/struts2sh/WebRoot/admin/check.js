// 文本检测(单控件版)
function checkS(objTemp,strTemp,strText,action)
{
  var bool = true;
  var actions = action.split('|');
  for(var i = 0;i < actions.length;i++)
  {
    if(actions[i] == "checknull")
    {
      bool = checkNull(objTemp,strTemp,strText);
      if(bool == false)
        return bool;
    }    if(actions[i] == "checkpintnumber")
    {
      bool = checkPositiveIntNumber(objTemp,strTemp,strText);
      if(bool == false)
        return bool;
    }
    if(actions[i] == "checkintnumber")
    {
      bool = checkIntNumber(objTemp,strTemp,strText);
      if(bool == false)
        return bool;
    }
    if(actions[i] == "checknumber")
    {
      bool = checkNumber(objTemp,strTemp,strText);
      if(bool == false)
        return bool;
    }
    if(actions[i] == "checkdate")
    {
      bool = checkDate(objTemp,strTemp,strText);
      if(bool == false)
        return bool;
    }
  }
  return bool;
}

// 检测是否为空值
function checkNull(objTemp,strTemp,strText)
{
	var temp = strTemp;
	temp = temp.trim();	if(temp == "")
	{
		alert(strText + "不能为空");
		return false;
	}
	else
		return true;
}

// 检测是否为整数
function checkPositiveIntNumber(objTemp,strTemp,strText)
{
	var result=strTemp.match(/^(-|\+)?\d+$/);
	if(result == null)
  {
    alert(strText + "必须是整数");    objTemp.focus();
    return false;
  }
  return true;
}

// 检测是否为正整数
function checkIntNumber(objTemp,strTemp,strText)
{	var result=strTemp.match(/^\d+$/);
  if(result == null)
  {
    alert(strText + "必须是正整数");    objTemp.focus();
    return false;
  }
  return true;
}

// 检测是否为数字
function checkNumber(objTemp,strTemp,strText)
{
  if(isNaN(strTemp))
  {
    alert(strText + "必须是数字");    objTemp.focus();
    return false;
  }
  else
    return true;
}

// 检测是否为日期
function checkDate(objTemp,strTemp,strText)
{
  if(isDateString(strTemp) == false)
  {
    alert(strText + "的日期格式错误或不是一个合法的日期")    objTemp.focus();
    return false;
  }
  else
    return true;
}

/******************************以下为加强功能函数******************************/

// 去除字符串的首尾的空格
String.prototype.trim=function()
{
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

// 去除字符串的左侧的空格
String.prototype.ltrim=function()
{
	return this.replace(/(^\s*)/g, "");
}


// 去除字符串的右侧的空格
String.prototype.rtrim=function()
{
	return this.replace(/(\s*$)/g, "");
}

// 去除字符串的首尾的%
String.prototype.trimPS=function()
{
	return this.replace(/(^%*)|(%*$)/g, "");
}

// 去除字符串的左侧的%
String.prototype.ltrimPS=function()
{
	return this.replace(/(^%*)/g, "");
}

// 去除字符串的右侧的%
String.prototype.rtrimPS=function()
{
	return this.replace(/(%*$)/g, "");
}

/******************************以下为适配函数******************************/

// 判断日期函数
function isDateString(sDate)
{
  var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31];
  var iaDate = new Array(3);
  var year, month, day;
  if (arguments.length != 1) return false;
  iaDate = sDate.toString().split("-");
  if (iaDate.length != 3) return false;
  if (iaDate[1].length > 2 || iaDate[2].length > 2) return false;
  year = parseFloat(iaDate[0]);
  month = parseFloat(iaDate[1]);
  day=parseFloat(iaDate[2]);
  if (year < 1900 || year > 2100) return false;
  if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
  if (month < 1 || month > 12) return false;
  if (day < 1 || day > iaMonthDays[month - 1]) return false;
  return true;
}

// 检查文本的长度(中文字为两个字符)
function checkTextLength(strTemp,textName,textLength)
{
  var str = strTemp;
  num=str.length;
  var arr=str.match(/[^\\\\\\\\\\\\\\\\x00-\\\\\\\\\\\\\\\\x80]/ig);
  if(arr!=null)num+=arr.length;
  if(parseInt(num) > parseInt(textLength))
  {
    alert(textName + "的长度不能大于" + textLength);
    return false;
  }
  else
    return true;
}
