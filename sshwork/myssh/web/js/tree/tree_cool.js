function alai_tree_cool(toObject,path)
{
	var icons=new alai_imagelist()
	icons.path=path
	icons.add("ball","leaf")
	icons.add("ball","twig")
	icons.add("ball","ball1")
	icons.add("ball2")
	icons.add("pisa_plus","expand")
	icons.add("icon_fix","collapse")
	var tree=new alai_tree(icons,0,toObject)
	tree.body.style.cssText="background:buttonface;width:180;background-image:url('"+path+"bg8.gif');border:2 solid buttonface"
	//if(typeof(toObject)=="object")toObject.style.cssText="background:buttonface;width:186;text-align:center;border:2 outset;padding:4 1 4 8;"
	var lastNode=null
	tree.afteradd=function(srcNode)
	{
		srcNode.body.style.cssText="text-align:left;width:100%;color:black;cursor:hand;margin:3;font-size:10pt;background-image:url('"+path+"bg8.gif');"
		if(srcNode.parent!=tree.root)
		{	srcNode.parent.body.style.cssText="text-align:left;width:100%;height:22;border:2 outset;color:black;background:buttonface;background-image:url('"+path+"btn_bg.gif');cursor:default;padding-top:2;font-size:10pt;"
			srcNode.parent.body.onclick=srcNode.parent.exIcon.onclick=srcNode.parent.label.click
		}
	}
	var exNode=null
	tree.onexpand=function(srcNode)
	{
		if(exNode!=null && srcNode!=exNode)exNode.expand(false);
		exNode=srcNode
	}
	tree.onmouseover=function(srcNode)
	{	if(!srcNode.hasChild)
		{
			srcNode.label.style.color="red"
			srcNode.exIcon.src=icons.item["ball2"].src
			srcNode.body.style.background="white"
		}
	}
	tree.onmouseout=function(srcNode)
	{	if(!srcNode.hasChild)
		{	srcNode.label.style.color="black"
			srcNode.body.style.background=""
			srcNode.exIcon.src=icons.item["ball1"].src
		}
	}

	tree.onselect=function(){return false;}
	return tree ;
}