/*** 
This is the menu creation code - place it right after you body tag
Feel free to add this to a stand-alone js file and link it to your page.
**/

//Menu object creation
oCMenu=new makeCM("oCMenu") //Making the menu object. Argument: menuname

oCMenu.frames = 0

//Menu properties   
oCMenu.pxBetween=15
oCMenu.fromLeft=180 
oCMenu.fromTop=0   
oCMenu.pxbetween=20
oCMenu.rows=1 
oCMenu.menuPlacement=0
                                                             
oCMenu.offlineRoot="file:///C|/Inetpub/wwwroot/dhtmlcentral/projects/coolmenus/examples/" 
oCMenu.onlineRoot="/coolmenus/" 
oCMenu.resizeCheck=0
oCMenu.wait=500 
oCMenu.fillImg="cm_fill.gif"
oCMenu.zIndex=200

//Background bar properties
oCMenu.useBar=0
oCMenu.barWidth="100%"
oCMenu.barHeight="menu" 
oCMenu.barClass="clBar"
oCMenu.barX=0 
oCMenu.barY=0
oCMenu.barBorderX=0
oCMenu.barBorderY=0
oCMenu.barBorderClass=""

//Level properties - ALL properties have to be spesified in level 0
oCMenu.level[0]=new cm_makeLevel() //Add this for each new level
oCMenu.level[0].width=110
oCMenu.level[0].height=18
oCMenu.level[0].regClass="clLevel0"
oCMenu.level[0].overClass="clLevel0over"
oCMenu.level[0].borderX=0
oCMenu.level[0].borderY=0
oCMenu.level[0].borderClass="clLevel0border"
oCMenu.level[0].offsetX=0
oCMenu.level[0].offsetY=0
oCMenu.level[0].rows=0
oCMenu.level[0].arrow=0
oCMenu.level[0].arrowWidth=0
oCMenu.level[0].arrowHeight=0
oCMenu.level[0].align="bottom"
//Property added ---- Doesn't apply to the top level. This gives you a border around all items.
oCMenu.level[0].roundBorder=0
oCMenu.level[0].filter="progid:DXImageTransform.Microsoft.Fade(duration=0.5); alpha(opacity=95);"


//EXAMPLE SUB LEVEL[1] PROPERTIES - You have to specify the properties you want different from LEVEL[0] - If you want all items to look the same just remove this
oCMenu.level[1]=new cm_makeLevel() //Add this for each new level (adding one to the number)
oCMenu.level[1].width=200
oCMenu.level[1].height=20
oCMenu.level[1].regClass="clLevel1"
oCMenu.level[1].overClass="clLevel1over"
oCMenu.level[1].borderX=0
oCMenu.level[1].borderY=1
oCMenu.level[1].align="bottom" 
oCMenu.level[1].offsetX=-4
oCMenu.level[1].offsetY=0
oCMenu.level[1].borderClass="clLevel1border"


//EXAMPLE SUB LEVEL[2] PROPERTIES - You have to spesify the properties you want different from LEVEL[1] OR LEVEL[0] - If you want all items to look the same just remove this
oCMenu.level[2]=new cm_makeLevel() //Add this for each new level (adding one to the number)
oCMenu.level[2].width=150
oCMenu.level[2].height=20
oCMenu.level[2].offsetX=0
oCMenu.level[2].offsetY=0
oCMenu.level[2].regClass="clLevel2"
oCMenu.level[2].overClass="clLevel2over"
oCMenu.level[2].borderClass="clLevel2border"