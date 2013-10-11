// JavaScript Document
var qTipTag = "label,li,a,div,img,td"; //Which tag do you want to qTip-ize? Keep it lowercase!//
var qTipX = 0; //This is qTip's X offset//
var qTipY = 15; //This is qTip's Y offset//

//There's No need to edit anything below this line//
tooltip = {
  name : "qTip_1",
  name_nowidth : "qTip_nowidth",
  offsetX : qTipX,
  offsetY : qTipY,
  flag : "text",
  tip : null,
  tip_nowidth : null
} 

tooltip.init = function () {
	var tipNameSpaceURI = "http://www.w3.org/1999/xhtml";
	if(!tipContainerID){ var tipContainerID = "qTip_1";}
	var tipContainer = document.getElementById(tipContainerID);
	if(!tipContainer) {
	  tipContainer = document.createElementNS ? document.createElementNS(tipNameSpaceURI, "div") : document.createElement("div");
		tipContainer.setAttribute("id", tipContainerID);
	  document.getElementsByTagName("body").item(0).appendChild(tipContainer);
	}

	//再创建一个div
	var tipContainer_nowidth = document.getElementById("qTip_nowidth");
	if(!tipContainer_nowidth) {
	  tipContainer_nowidth = document.createElementNS ? document.createElementNS(tipNameSpaceURI, "div") : document.createElement("div");
		tipContainer_nowidth.setAttribute("id", "qTip_nowidth");
	  document.getElementsByTagName("body").item(0).appendChild(tipContainer_nowidth);
	}

	if (!document.getElementById) return;
	this.tip = document.getElementById (this.name);
	this.tip_nowidth = document.getElementById(this.name_nowidth);//添加
	if (this.tip) document.onmousemove = function (evt) {tooltip.move (evt)};
	//if (this.tip_nowidth) document.onmousemove = function (evt) {tooltip.move_nowidth (evt)};

	var a, sTitle, elements;
	
	var elementList = qTipTag.split(",");
	for(var j = 0; j < elementList.length; j++)
	{	
		elements = document.getElementsByTagName(elementList[j]);
		if(elements)
		{
			for (var i = 0; i < elements.length; i ++)
			{
				a = elements[i];
				sTitle = a.getAttribute("title");
				if(document.all){
					if(sTitle!=''){
						if(sTitle.slice(-3)=="gif" || sTitle.slice(-3)=="jpg" || sTitle.slice(-3)=="png"){this.flag='img';}
					}
				}else{
					if(sTitle!=null){
						if(sTitle.slice(-3)=="gif" || sTitle.slice(-3)=="jpg" || sTitle.slice(-3)=="png"){this.flag='img';}
					}
				}
				if(sTitle)
				{
					a.setAttribute("tiptitle", sTitle);
					a.removeAttribute("title");
					if(elementList[j]=="li"){a.setAttribute("type","li");}else{a.setAttribute("type","other");}
					a.removeAttribute("alt");
					a.onmouseover = function() {tooltip.show(this.getAttribute('tiptitle'),this.getAttribute('type'))};
					a.onmouseout = function() {tooltip.hide(this.getAttribute('type'))};
				}
			}
		}
	}
}

tooltip.move = function (evt) {
	var x=0, y=0;
	if (document.all) {//IE
		x = (document.documentElement && document.documentElement.scrollLeft) ? document.documentElement.scrollLeft : document.body.scrollLeft;
		y = (document.documentElement && document.documentElement.scrollTop) ? document.documentElement.scrollTop : document.body.scrollTop;
		x += window.event.clientX;
		y += window.event.clientY;
		var vNowWidth = parseInt(parseInt(x)+180);
		if(document.body.scrollWidth<vNowWidth){
			var vDiff = parseInt(vNowWidth)-parseInt(document.body.scrollWidth);
			x=parseInt(x)-parseInt(vDiff);
		}
	} else {//Good Browsers
		x = evt.pageX;
		y = evt.pageY;
		var vNowWidth_f = parseInt(parseInt(x)+180);
		if(document.body.scrollWidth<vNowWidth_f){
			var vDiff = parseInt(vNowWidth_f)-parseInt(document.body.scrollWidth);
			x=parseInt(x)-parseInt(vDiff);
		}
	}
	//alert(x);
	this.tip.style.left = (x + this.offsetX) + "px";
	this.tip.style.top = (y + this.offsetY) + "px";
	this.tip_nowidth.style.left = (x + this.offsetX) + "px";
	this.tip_nowidth.style.top = (y + this.offsetY) + "px";
}

tooltip.show = function (text,type) {
	if (!this.tip) return;
	if(type=="li"){
		if(this.flag=="img"){	
			var oTxt = document.createElement("img");
			var oLoader = document.createElement("img");
			var oImg = new Image();
			oLoader.src = "http://www.dzart.net/View/Default/Image/loader.gif";
			this.tip_nowidth.appendChild(oLoader);
			oImg.onload = function(){
				var qTip_1 = document.getElementById('qTip_nowidth');
				oTxt.src="http://img1.dzart.net:81/Upload/"+text;
				qTip_1.innerHTML="";
				qTip_1.appendChild(oTxt);
				//alert(text);
			}
			oImg.src = "http://img1.dzart.net:81/Upload/"+text;
			if(document.all){
			oTxt.style.height="100";
			oTxt.style.width="150";
			}else{
			oTxt.height="100";
			oTxt.width="150";
			}
			
			//oTxt.src="http://img1.dzart.net:81/Upload/"+text;
			//this.tip_nowidth.appendChild(oTxt);
		}else{
			this.tip_nowidth.innerHTML = text;
		}
		this.tip_nowidth.style.display = "block";
	}else{
		this.tip.innerHTML = text;
		this.tip.style.display = "block";
	}
}

tooltip.hide = function (type) {
	if (!this.tip) return;
	if(type=="li"){
		this.tip_nowidth.innerHTML = "";
		this.tip_nowidth.style.display = "none";
	}else{
		this.tip.innerHTML = "";
		this.tip.style.display = "none";
	}
}

window.onload = function () {
	tooltip.init ();
}