
function alai_tree_radio()
{
	if(typeof(alai_tree)!="function")
	{
		alert("run alai_tree_radio() fail, please load alai_tree firt!")
		return
	}
	var colRdoNode=[]
	alai_tree.prototype.colRdoNode=colRdoNode
	alai_tree.prototype.addRdoNode=function(toNode,text,key,ico,name,exeCategory,exeArg,checked,disabled)
	{
		var newNode=this.add(toNode,"last",text,key,ico,exeCategory,exeArg)
		if(name=="")
		{
		  var rdoBox=document.createElement('<input id="tree" class="none" name="tree" type="Radio">')
		  }
		else
		{
		  var rdoBox=document.createElement('<input id="tree" class="none" name="'+name+'" type="Radio">')
		  }
		var tree=this
		newNode.label.insertAdjacentElement("beforeBegin",rdoBox)
		newNode.isCheck=true
		if(typeof(checked)=="boolean")rdoBox.checked=checked;
		if(typeof(disabled)=="boolean")rdoBox.disabled=disabled;
		newNode.oncheck=new Function("return true;")
		rdoBox.onmousedown=function(){if(newNode.oncheck())tree.onchbclick(newNode)}
		rdoBox.onpropertychange=function(){if(newNode.oncheck())tree.oncheck(newNode)}
		
		colRdoNode[colRdoNode.length]=newNode
		newNode.rdoBox=rdoBox
		return newNode
	}
	
	alai_tree.prototype.onchbclick=new Function("return true;")
	alai_tree.prototype.oncheck=new Function("return true;")
}
alai_tree_radio()