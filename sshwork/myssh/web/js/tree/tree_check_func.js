
function alai_tree_check()
{
	if(typeof(alai_tree)!="function")
	{
		alert("run alai_tree_check() fail, please load alai_tree firt!")
		return
	}
	var colChkNode=[]
	alai_tree.prototype.colChkNode=colChkNode
	alai_tree.prototype.addChkNode=function(toNode,text,key,ico,exeCategory,exeArg,checked,disabled)
	{
		var newNode=this.add(toNode,"last",text,key,ico,exeCategory,exeArg)
		var chkBox=document.createElement('<input border="0" type="Checkbox">')
		var tree=this
		newNode.label.insertAdjacentElement("beforeBegin",chkBox)
		newNode.isCheck=true
		if(typeof(checked)=="boolean")chkBox.checked=checked;
		if(typeof(disabled)=="boolean")chkBox.disabled=disabled;
		newNode.oncheck=new Function("return true;")
		chkBox.onmousedown=function(){if(newNode.oncheck())tree.onchbclick(newNode)}
		chkBox.onpropertychange=function(){if(newNode.oncheck())tree.oncheck(newNode)}
		
		colChkNode[colChkNode.length]=newNode
		newNode.checkBox=chkBox
		return newNode
	}
	
	alai_tree.prototype.onchbclick=new Function("return true;")
	alai_tree.prototype.oncheck=new Function("return true;")
}
alai_tree_check()