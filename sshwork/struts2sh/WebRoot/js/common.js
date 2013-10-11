function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

function MM_callJS(jsStr) { //v2.0
  return eval(jsStr)
}

function MM_popupMsg(msg) { //v1.0
    alert(msg);
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features).focus();
}

function SYNX_showModalDialog(theURL,args,features) { // added by Clark Lu, 05/15/2003, for modal window
  if(ie) {
    return window.showModalDialog(theURL,args,features);
  }
  else {
    return MM_openBrWindow(theURL,'modal dialog',features);
  }
}

function MM_validateForm() { //v4.0
  var i,p,q,nm,test,num,min,max,errors='',args=MM_validateForm.arguments;
  for (i=0; i<(args.length-2); i+=3) { test=args[i+2]; val=MM_findObj(args[i]);
    if (val) { nm=val.name; if ((val=val.value)!="") {
      if (test.indexOf('isEmail')!=-1) { p=val.indexOf('@');
        if (p<1 || p==(val.length-1)) errors+='- '+nm+' must contain an e-mail address.\n';
      } else if (test!='R') { num = parseFloat(val);
        if (isNaN(val)) errors+='- '+nm+' must contain a number.\n';
        if (test.indexOf('inRange') != -1) { p=test.indexOf(':');
          min=test.substring(8,p); max=test.substring(p+1);
          if (num<min || max<num) errors+='- '+nm+' must contain a number between '+min+' and '+max+'.\n';
    } } } else if (test.charAt(0) == 'R') errors += '- '+nm+' is required.\n'; }
  } if (errors) alert('The following error(s) occurred:\n'+errors);
  document.MM_returnValue = (errors == '');
}

function tmd_findObj(n) {
    var x,t; if((n.indexOf("?"))>0&&parent.frames.length){t=n.split("?");
    x=eval("parent.frames['"+t[1]+"'].document.getElementById('"+t[0]+"')");
    }else{x=document.getElementById(n)}return x;
}

function tmd_DivMove(theDiv, l, t) {
    var x = (document.layers) ? ".left" : ".style.left";
    var y = (document.layers) ? ".top" : ".style.top";
    var fun = (document.getElementById) ? "tmd_findObj" : "MM_findObj";
    var obj = eval(fun+"(theDiv)");
    if(obj) {
      if(l) eval(fun+"('"+theDiv+"')"+x+"="+l);
      if(t) eval(fun+"('"+theDiv+"')"+y+"="+t);
    }
}

function tmd_SwitchDiv(theDiv, l1, l2, t1, t2) { // written by Clark Lu, switch div between two positions
    var x = ".style.left";
    var y = ".style.top";
    
    var fun = (document.getElementById) ? "tmd_findObj" : "MM_findObj";
    var obj = eval(fun+"(theDiv)");
    if(obj){
    	
      if(l1) {
        var oldL = eval(fun+"('"+theDiv+"')"+x);
        //oldL = (oldL.substring(0, oldL.indexOf('px'))==(""+l1)) ? l2 : l1;
        oldL = (oldL==l1 || oldL.substring(0, oldL.indexOf('px'))==(""+l1)) ? l2 : l1;
        eval(fun+"('"+theDiv+"')"+x+"='"+oldL + "px'");
      }
      if(t1) {
        var oldT = eval(fun+"('"+theDiv+"')"+y);
        oldT = (oldT.substring(0, oldT.indexOf('px'))==(""+t1)) ? t2 : t1;
        eval(fun+"('"+theDiv+"')"+y+"='"+oldT + "px'");
      }
    }
}

function MM_showHideLayers() { //v3.0 revised by Clark Lu for switch show and hide
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3)
    if ((obj=MM_findObj(args[i]))!=null) {
      v=args[i+2];
      if (obj.style) {
        obj=obj.style;
        if(!v)
          v=(obj.visibility=="hidden")?"visible":"hidden";
        else
          v=(v=='show')?'visible':(v='hide')?'hidden':v;
      }
      else if(!v)
        v=(obj.visibility=="hide")?"show":"hide";

      obj.visibility=v;
  }
}

function expendCollapseLayers() { // written by Clark Lu
  var i,p,v,obj,args=expendCollapseLayers.arguments;
  for (i=0; i<(args.length-2); i+=3)
    if ((obj=MM_findObj(args[i]))!=null) {
        v=args[i+2];
        if (obj.style) {
            obj=obj.style;
            if(!v)
                v=(obj.display=="none")?"block":"none";
            else
                v=(v=='show')?'block':(v='hide')?'none':v;
        }
        obj.display=v;
    }
}

function isLayerVisible(name) { // written by Clark Lu
  if ((obj=MM_findObj(name))!=null) {
    if (obj.style)
      obj=obj.style;
    return (obj.visibility!="hidden");
  }
  return false;
}

function isLayerExpended(name) { // written by Clark Lu
  if ((obj=MM_findObj(name))!=null) {
    if (obj.style)
      obj=obj.style;
    return (obj.display!="none");
  }
  return false;
}

function switchImage() { // written by Clark Lu
  var i,x,a=switchImage.arguments; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){
   if(srcEquals(x.src, a[i+1])) x.src=a[i+2]; else x.src=a[i+1];}
}

function srcEquals(str1, str2) { // written by Clark Lu
  var endStr1 = str1.substring(str1.lastIndexOf('/'), str1.length);
  var endStr2 = str2.substring(str2.lastIndexOf('/'), str2.length);

  return (endStr1==endStr2);
}

function isDigit(obj) {
    slen=obj.length;
    for (var position=0; position<slen; position++){
        cc = obj.charAt(position);
        if (cc <"0" || cc >"9")
        {
            return false;
        }
    }
    return true;
}

function setEnable(objName, bool) {
  var obj = MM_findObj(objName);
  disable(obj, !bool);
}

function disable() {
  var args=disable.arguments;
  var bool = true;
  if(args.length > 0)
    obj = args[0];
  if(args.length > 1)
    bool = args[1];
  
  if(obj){
    if(obj.type == 'select-one'){
      obj.disabled=bool;
    }else if(obj.length){
      var i;
      for(i=0;i<obj.length;++i)
        obj[i].disabled=bool;
    }else{
      obj.disabled=bool;
    }
  }
}

// added by Clark Lu, 05/29/2003
// checkboxName is the name of checkbox with a form name, such as:
//   document.formName.checkboxName
// the return value is boolean, which is true if at least one checkbox is checked
function isChecked(checkboxName) {
    if( checkboxName == null )
      return false;
    if( !checkboxName.length ) {  // one item
        return checkboxName.checked;
    }
    else {  // muti items
      var i;
      for (i=0; i<checkboxName.length; ++i) {
        if (checkboxName[i].checked)
            return true;
      }
    }

    return false;
}

// added by Clark Lu, 05/29/2003
// 'checkboxName' is the name of checkbox with a form name, such as:
//   document.formName.checkboxName
// 'delimiter' is the delimiter for return String
// the return value is String, include all values which is checked
// such as '232;5345;43534;6456;5333', here ';' is the delimiter
function getAllCheckedValues(checkboxName, delimiter) {
    if( checkboxName == null )
        return null;

    if( !checkboxName.length ) {  // one item
        if( checkboxName.checked )
            return checkboxName.value;
        else
            return null;
    }
    else {  // muti items
        var i, count = 0;
        var skus = '';
        for (i=0; i<checkboxName.length; i++) {
            if (checkboxName[i].checked) {
                if (count > 0)
                    skus = skus + delimiter;

                skus = skus + checkboxName[i].value;
                count++;
            }
        }

        return skus;
    }
}

function setBorderOfEnable(objName, bool) {
  var obj = MM_findObj(objName);
  if (bool)
      obj.style.borderColor = "#404040";
  else
      obj.style.borderColor = "#a0a0a0";
}

function validEMail(objName) {
  var obj = MM_findObj(objName);
  if (!obj)
    return false;

  var val = obj.value;
  if (val.indexOf("@",0) < 0 || val.indexOf(".")<0)
    return false;
  else
     return true;
}

function setFocus(objName, bool) {
  var obj = MM_findObj(objName);

  if (bool) {
    obj.focus();
    obj.select();
  }
  else {
    obj.blur();
  }
}

function clearForm(frm){
  var elements=frm.elements;
  var element;
  var i;
  for(i=0;i<elements.length;++i){
    element=elements[i];
    if(element.type=="text"||element.type=="textarea")
      element.value="";
    else if(element.type=="select-one"||element.type=="select-multiple"){
      var options=element.options,j,item;
      for(j=0;j<options.length;++j){
        item=options[j];
        item.selected=item.defaultSelected;
      }
    }else if(element.type=="checkbox"||element.type=="radio")
      element.checked=element.defaultChecked;
  }
}


//NEC Agent Begin
//Used to format end user price
function roundFloat(value,scale)
{
   var result = 0;
   if(value >= 0)
   {
      val1 = Math.floor(value);
      val2 = Math.round(((value - val1) * Math.pow(10,scale)));
      result = val1 + val2 / Math.pow(10,scale);
   }
   else
   {
      tmpValue = Math.abs(value);
      val1 = Math.floor(tmpValue);
      val2 = Math.round(((tmpValue - val1) * Math.pow(10,scale)));
      result = val1 + val2 / Math.pow(10,scale);
      result = result * -1;
   }

   result="" + result;
   var place = result.indexOf(".");
   if(place >0){
   	var decimal = result.substring(place+1,result.length);
   	if(decimal.length<2)result = result + '0';
   }else{
   	result = result + ".00"
   }
   return result;
   }

function isNumeric(obj) {
    slen=obj.length;
    for (i=0; i<slen; i++){
        cc = obj.charAt(i);
        if ((cc <"0" || cc >"9")&&cc!=".")
        {
            return false;
        }
    }
    return true;
}
function isLoopYear(year)  // added by eric yu
{
	if (year%400 == 0) return true;
	if (year%4==0 && year%100!=0) return true;
	return false;
}
function isValidDate(dateStr)  // added by eric yu
{
    // date format 'mm/dd/yyyy'
    var separator = "/";
    if (dateStr == "") return true;

    for (i=0; i<dateStr.length; i++){
        cc = dateStr.charAt(i);
        if ((cc <"0" || cc >"9") && cc != separator)
        {
            return false;
        }
    }

    var arrayOfStrings = dateStr.split(separator);
    var month = arrayOfStrings[0];
    var day = arrayOfStrings[1];
    var year = arrayOfStrings[2];

    if ( !isDigit(year) ) return false;
    if ( !isDigit(month) || month>12 || month<1) return false;
    if ( !isDigit(day) || day>31 || day<1) return false;
    if (day>30 && (month==4 || month==6 || month==9 || month==11)) return false;
    if (day>29 && month==2 ) return false;
    if (day>28 && month==2 && !isLoopYear(year,month)) return false;
    return true;

}
//NEC Agent End


function deleteXToolsCookies(){
  deleteCookie('c_xTools',contextPath);
  deleteCookie('c_productSearch',contextPath);
  deleteCookie('c_productPnA',contextPath);
  deleteCookie('c_orderSearch',contextPath);
  deleteCookie('c_shopCart',contextPath);
}

function getKeywordCount(chars)
{
    var tmpLength = 0;

    var keywordCount = 0;

    var inQuota = false;
    var newKeywordFlag = true;

	if (chars == null){
		return keywordCount;
	}
    for(i = 0; i < chars.length; i ++)
    {
        var cc = chars.charAt(i);
        if (cc == '"' )
        {
            if (!inQuota)
            {
                inQuota = true;
            }
            else
            {
                inQuota = false;
                tmpLength = 0;
                keywordCount ++;

            }
            newKeywordFlag = true;
        }
        else if (!inQuota && (
                cc == ' '
                || cc == '\n'
                || cc == '\t'
                || cc == '\r'
                || cc == ','
                || cc == ';')
                )
        {
            if (tmpLength > 0)
            {
                tmpLength = 0;
                keywordCount ++;
            }
        }
        else
            tmpLength ++;

    }
    if (tmpLength > 0)
    {
        keywordCount ++;
    }
    return keywordCount;
}

function getKeywordWithQtyCount(chars)
{
    var tmpLength = 0;

    var keywordCount = 0;

    var inQuota = false;
    var newKeywordFlag = true;

	if (chars == null){
		return keywordCount;
	}
    for(i = 0; i < chars.length; i ++)
    {
        var cc = chars.charAt(i);
        if (cc == '"' )
        {
            if (!inQuota)
            {
                inQuota = true;
            }
            else
            {
                inQuota = false;
                tmpLength = 0;
                keywordCount ++;

            }
            newKeywordFlag = true;
        }
        else if (!inQuota && (
                cc == cc == '\n'
                || cc == '\r'
                || cc == ','
                || cc == ';')
                )
        {
            if (tmpLength > 0)
            {
                tmpLength = 0;
                keywordCount ++;
            }
        }
        else
            tmpLength ++;

    }
    if (tmpLength > 0)
    {
        keywordCount ++;
    }
    return keywordCount;
}

//Remove string leading and trailing spaces
//Added by Cheppin, 2004/09/07
function trim(s) 
{
  // Remove leading spaces and carriage returns
  
  while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
  {
    s = s.substring(1,s.length);
  }

  // Remove trailing spaces and carriage returns

  while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
  {
    s = s.substring(0,s.length-1);
  }
  return s;
}
/* Modify the qtyOnkeypress() to fit the firefox
function qtyOnkeypress() { 
   if ( event.keyCode < 48 || event.keyCode > 57 )
     event.keyCode = 8        
}
*/
function qtyOnkeypress(event) { 
	var key=event.keyCode||event.which;
	if ( key>=48&&key<=57 ||key==8||key==46)
 		return true;
	else
		return false;    
}

/*

IsInt(string,string,int or string):(String,+/- or empty,empty or 0)
*/

function IsInt(objStr,sign,zero)
{
    var reg;    
    var bolzero; 
    if(trim(objStr)=="")
    {
        return false;
    }else{
        objStr=objStr.toString();
    }
    if((sign==null)||(trim(sign)=="")){
        sign="+-";
    }
    if((zero==null)||(trim(zero)=="")){
        bolzero=false;
    }else{
        zero=zero.toString();
        if(zero=="0"){
            bolzero=true;
        }else{
            alert(objStr+" is not a valid integer");
        }
    }
    switch(sign){
        case "+-":
            reg=/(^-?|^\+?)\d+$/;
            break;
        case "+": 
            if(!bolzero){
                reg=/^\+?[0-9]*[1-9][0-9]*$/;
            }else{
                reg=/^\+?[0-9]*[0-9][0-9]*$/;
            }
            break;
        case "-":
            if(!bolzero){
                reg=/^-[0-9]*[1-9][0-9]*$/;
            }else{
                reg=/^-[0-9]*[0-9][0-9]*$/;
            }
            break;
        default:
            alert(objStr+" is not a valid integer");
            return false;
            break;
    }  
    var r=objStr.match(reg);
    if(r==null){
        return false;
    }else{ 
        return true; 
    }
}

function getXMLHTTPObject() {
	var xmlHttpObj = false;
    // branch for native XMLHttpRequest object
    if(window.XMLHttpRequest) {
        try {
            xmlHttpObj = new XMLHttpRequest();
        } catch(e) {
            xmlHttpObj = false;
        }
    // branch for IE/Windows ActiveX version
    } else if(window.ActiveXObject) {
        try {
            xmlHttpObj = new ActiveXObject("Msxml2.XMLHTTP");
        } catch(e) {
            try {
                xmlHttpObj = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(e) {
                xmlHttpObj = false;
            }
        }
    }
    
    return xmlHttpObj;
}

//table sort begin
//<--------------
var compCount = 0;
var swapCount = 0;

function getColumn(o) {
	var cs = document.getElementById("MainTable").rows[0].cells;

	for (var i=0;i < cs.length; i++) {
		if (cs[i] == o.parentNode)
			return i;
	}
	//if not match then return 0
	return 0;
}

function sortTable(id,c,r,tp) {
	var t = document.getElementById(id);
	if (!t)
		return false;

	// The first time this function is called for a given table, set up an
	// array of reverse sort flags.
	if (t.reverseSort == null) {
		t.reverseSort = new Array();
		// Also, assume the team name column is initially sorted.
		t.lastColumn = 1;
	}

	// If this column has not been sorted before, set the initial sort direction.
	if (t.reverseSort[c] == null)
		t.reverseSort[c] = r;

	// If this column was the last one sorted, reverse its sort direction.
	if (c == t.lastColumn)
		t.reverseSort[c] = !t.reverseSort[c];

	// Remember this column as the last one sorted.
	t.lastColumn = c;

	// Set the table display style to "none" - necessary for Netscape 6 
	// browsers.
	
	var theTable = new Array();
	var i;
	for(i=t.rows.length-1;i>=0;i--) 
		theTable[i] = new oRow(getTextValue(t.rows[i].cells[c]),t.rows[i],tp,t.reverseSort[c]);
	
	theTable.sort(compareValues);

	for(i=0;i<theTable.length;i++) 
		t.appendChild(theTable[i].row);

	return false;
}

function oRow (txt,row,tp,rs) {
	this.txt = txt;
	this.row = row;
	this.tp  = tp;
	this.rs  = rs;
}

function compareValues(o1,o2) {
	compCount++;
    
	var v1 = o1.txt;
	var v2 = o2.txt;
	var tp = o1.tp;
	var rs = o1.rs;
    var f1, f2, result;

	// If the values are numeric, convert them to floats.
	if ( tp == 'M' ) {
		/*v1 = v1.replace(/\$|,|\)/g,'');    
		v2 = v2.replace(/\$|,|\)/g,'');  
		v1 = v1.replace(/\(/g,'-');    
		v2 = v2.replace(/\(/g,'-');  */
		v1 = convertCurrency(v1);
		v2 = convertCurrency(v2);
	}

	if ( tp == 'M' || tp == 'N'){  
		f1 = parseFloat(v1);
		f2 = parseFloat(v2);

		v1 = f1;
		v2 = f2;
	}

	if (tp == 'D') {
		if (v1 == null || v1 == "")
			v1 = "1/1/00";
		if (v2 == null || v2 == "")
			v2 = "1/1/00";

		d1 = new Date(v1);
		d2 = new Date(v2);
		
		v1 = d1.valueOf();
		v2 = d2.valueOf();
	}

    // Compare the two values.
	if (v1 < v2)
		result =  -1;
	else if (v1 > v2)
		result =  1
	else 
		result =  0;

	if (rs)
		return -result;
	else 
		return result;
}

function convertCurrency( v ) {
	var m = '',c;
	for ( var i = 0; i < v.length; i ++ ) {
		c = v.charAt(i);
		if ( c == '$' || c == ',' || c == '(' || c == ')') 
		  continue;
		else
		  m += c;
	}
	return m;  
}

function getTextValue(el) {
	if (ie5) {
		return normalizeString(el.innerText);
	}

  // Find and concatenate the values of all text nodes contained within the
  // element.
  var s = "";
  for (var i = 0; i < el.childNodes.length; i++)
    if (el.childNodes[i].nodeType == document.TEXT_NODE)
      s += el.childNodes[i].nodeValue;
    else if (el.childNodes[i].nodeType == document.ELEMENT_NODE &&
             el.childNodes[i].tagName == "BR")
      s += " ";
    else
      // Use recursion to get text within sub-elements.
      s += getTextValue(el.childNodes[i]);

  return normalizeString(s);
}

// Regular expressions for normalizing white space.
var whtSpEnds = new RegExp("^\\s*|\\s*$", "g");
var whtSpMult = new RegExp("\\s\\s+", "g");
function normalizeString(s) {
  s = s.replace(whtSpMult, " ");  // Collapse any multiple whites space.
  s = s.replace(whtSpEnds, "");   // Remove leading or trailing white space.
  return s;
}
//-------------->
//table sort end......

/*this function is handle that we relace the link <a href="" target="_blank"> to <a href="" rel="external"> for docType */
function externallinks() { 
 if (!document.getElementsByTagName) return; 
 var anchors = document.getElementsByTagName("a"); 
 for (var i=0; i<anchors.length; i++) { 
   var anchor = anchors[i]; 
   if (anchor.getAttribute("href") && 
       anchor.getAttribute("rel") == "external") 
     anchor.target = "_blank"; 
 } 
} 
/*this function is handle that we relace the link <a href="" target="_parent"> to <a href="" rel="parent"> for docType */
function parentlinks() { 
 if (!document.getElementsByTagName) return; 
 var anchors = document.getElementsByTagName("a"); 
 for (var i=0; i<anchors.length; i++) { 
   var anchor = anchors[i]; 
   if (anchor.getAttribute("href") && 
       anchor.getAttribute("rel") == "parent") 
     anchor.target = "_parent"; 
 } 
} 
//if you want to call the DrawImage(this,80,28) in the original version. now you should use <img name="fit_80_28" src="" alt=""> to invoke it.
function fitImage(){
	var imgArr = document.getElementsByTagName("img");
	for(var i=0;i<imgArr.length;i++){
		var name = imgArr[i].name;
		if(name.indexOf("fit_")==0){
			var strArr = name.split("_");
			var width = parseInt(strArr[1]);
			var height = parseInt(strArr[2]);
			fitImageSize(imgArr[i],width,height);
		}
	}
}

function fitImageSize(ImgD,FitWidth,FitHeight){
	var image=new Image();
	if(Browser.IE) image.onerror=function(){image.width=0;image.height=0;}
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		if(image.width/image.height>= FitWidth/FitHeight){
			if(image.width>FitWidth){
				ImgD.width=FitWidth;
				ImgD.height=(image.height*FitWidth)/image.width;
			} else{
				ImgD.width=image.width;
				ImgD.height=image.height;
			}
		} else{
			if(image.height>FitHeight){
				ImgD.height=FitHeight;
				ImgD.width=(image.width*FitHeight)/image.height;
			} else{
				ImgD.width=image.width;
				ImgD.height=image.height;
			}
		}
	} 
}

function addWindowEvent(e,f){
	var p = window[e]
	window[e] = (p?(function(){p();f();}):f);
}
addWindowEvent("onload",externallinks);

 //Browser type;
var USER_AGENT = navigator.userAgent;
var Browser = {
         Opera:!!window.opera,
         IE:USER_AGENT.indexOf("MSIE ")>=0,
         IE7:USER_AGENT.indexOf("MSIE 7")>=0,
         IE6:USER_AGENT.indexOf("MSIE 6")>=0,
         Firefox:USER_AGENT.indexOf("Firefox/")>=0,
         Firefox2:USER_AGENT.indexOf("Firefox/2")>=0,
         Firefox1:USER_AGENT.indexOf("Firefox/1")>=0
}

        
 // Hide all select boxes in IE6
function hideSelect(){
        if(Browser.IE6){
            var selectBoxs=document.getElementsByTagName("select");
	        for (var selectIdx=0; selectIdx<selectBoxs.length; selectIdx++)
	        {	            
	          selectBoxs[selectIdx].style.visibility = "hidden"; 	         
            }
        }
}

// Unhide all select boxes in IE6
function unhideSelect(){    
      if(Browser.IE6){	       
	        var selectBoxs=document.getElementsByTagName("select");
	        for (var selectIdx=0; selectIdx<selectBoxs.length; selectIdx++)
	        {	            
	          selectBoxs[selectIdx].style.visibility = "visible"; 	         
            }
        }
}



/**
 *	Whatever:hover - V1.41.050927 - hover & active
 *	------------------------------------------------------------
 *	(c) 2005 - Peter Nederlof
 *	Peterned - http://www.xs4all.nl/~peterned/
 *	License  - http://creativecommons.org/licenses/LGPL/2.1/
 *
 *	Whatever:hover is free software; you can redistribute it and/or
 *	modify it under the terms of the GNU Lesser General Public
 *	License as published by the Free Software Foundation; either
 *	version 2.1 of the License, or (at your option) any later version.
 *
 *	Whatever:hover is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *	Lesser General Public License for more details.
 *
 *	Credits and thanks to:
 *	Arnoud Berendsen, Martin Reurings, Robert Hanson
 *
 *	howto: body { behavior:url("csshover.htc"); }
 *	------------------------------------------------------------
 */

var csshoverReg = /(^|\s)(([^a]([^ ]+)?)|(a([^#.][^ ]+)+)):(hover|active)/i,
currentSheet, doc = window.document, hoverEvents = [], activators = {
	onhover:{on:'onmouseover', off:'onmouseout'},
	onactive:{on:'onmousedown', off:'onmouseup'}
}

function parseStylesheets() {
	if(!/MSIE (5|6|7)/.test(navigator.userAgent)) return;
	window.attachEvent('onunload', unhookHoverEvents);
	var sheets = doc.styleSheets, l = sheets.length;
	for(var i=0; i<l; i++) 
		parseStylesheet(sheets[i]);
}
	function parseStylesheet(sheet) {
		if(sheet.imports) {
			try {
				var imports = sheet.imports, l = imports.length;
				for(var i=0; i<l; i++) parseStylesheet(sheet.imports[i]);
			} catch(securityException){}
		}

		try {
			var rules = (currentSheet = sheet).rules, l = rules.length;
			for(var j=0; j<l; j++) parseCSSRule(rules[j]);
		} catch(securityException){}
	}

	function parseCSSRule(rule) {
		var select = rule.selectorText, style = rule.style.cssText;
		if(!csshoverReg.test(select) || !style) return;
		
		var pseudo = select.replace(/[^:]+:([a-z-]+).*/i, 'on$1');
		var newSelect = select.replace(/(\.([a-z0-9_-]+):[a-z]+)|(:[a-z]+)/gi, '.$2' + pseudo);
		var className = (/\.([a-z0-9_-]*on(hover|active))/i).exec(newSelect)[1];
		var affected = select.replace(/:(hover|active).*$/, '');
		var elements = getElementsBySelect(affected);
		if(elements.length == 0) return;

		currentSheet.addRule(newSelect, style);
		for(var i=0; i<elements.length; i++)
			new HoverElement(elements[i], className, activators[pseudo]);
	}

function HoverElement(node, className, events) {
	if(!node.hovers) node.hovers = {};
	if(node.hovers[className]) return;
	node.hovers[className] = true;
	hookHoverEvent(node, events.on, function() { node.className += ' ' + className; });
	hookHoverEvent(node, events.off, function() { node.className = node.className.replace(new RegExp('\\s+'+className, 'g'),''); });
}
	function hookHoverEvent(node, type, handler) {
		node.attachEvent(type, handler);
		hoverEvents[hoverEvents.length] = { 
			node:node, type:type, handler:handler 
		};
	}

	function unhookHoverEvents() {
		for(var e,i=0; i<hoverEvents.length; i++) {
			e = hoverEvents[i]; 
			e.node.detachEvent(e.type, e.handler);
		}
	}

function getElementsBySelect(rule) {
	var parts, nodes = [doc];
	parts = rule.split(' ');
	for(var i=0; i<parts.length; i++) {
		nodes = getSelectedNodes(parts[i], nodes);
	}	return nodes;
}
	function getSelectedNodes(select, elements) {
		var result, node, nodes = [];
		var identify = (/\#([a-z0-9_-]+)/i).exec(select);
		if(identify) return [doc.getElementById(identify[1])];
		
		var classname = (/\.([a-z0-9_-]+)/i).exec(select);
		var tagName = select.replace(/(\.|\#|\:)[a-z0-9_-]+/i, '');
		var classReg = classname? new RegExp('\\b' + classname[1] + '\\b'):false;
		for(var i=0; i<elements.length; i++) {
			result = tagName? elements[i].all.tags(tagName):elements[i].all; 
			for(var j=0; j<result.length; j++) {
				node = result[j];
				if(classReg && !classReg.test(node.className)) continue;
				nodes[nodes.length] = node;
			}
		}	return nodes;
	}

function fixIE6TextSelectBug(){
document.body.style.height = document.documentElement.scrollHeight + 'px'; 
}
function isValidEMail(email){
    return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email);
}
function trim(str)
{
  return str.replace(/(^\s*)|(\s*$)/g, ""); 
}