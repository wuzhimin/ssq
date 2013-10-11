var exclude=true; var agt=navigator.userAgent.toLowerCase();
var ie=false; var ie4=false; var ie5=false; var op5=false; var konqi=false;
if (typeof document.all!="undefined"&&(agt.indexOf('msie')!=-1)) {
ie=true; ie4=true; exclude=false;
if (agt.indexOf('msie 4')==-1) { ie5=true; ie4=false; }
if (agt.indexOf('opera')!=-1) { ie=false; ie4=false; ie5=false; op5=true; }
}
var ns6=false; var mz7=false;
if (typeof document.getElementById!="undefined"&&!ie) {
ns6=true; exclude=false;
if (agt.indexOf('netscape6')==-1 && agt.indexOf('netscape/7')==-1) {
ns6=false; mz7=true;
}
if (typeof window.opera!="undefined") { mz7=false; op5=true;}
else if (agt.indexOf('gecko')==-1) { mz7=false; exclude=true; }
}
if (agt.indexOf('opera 4')!=-1) { op5=false; mz7=false; exclude=true; }
var ns4=false;
if ((agt.indexOf('mozilla')!=-1)&&(parseInt(navigator.appVersion)>=4)&&!ie&&!op5&&!ns6&&!mz7) {
ns4=true; exclude=false;
}
if (agt.indexOf('webtv')!=-1) { ie=false; ie4=false; exclude=true; }
var win=false; var mac=false; var lin=false;
if (agt.indexOf('win')!=-1) { win=true; mac=false; lin=false; }
else if (agt.indexOf('mac')!=-1) { win=false; mac=true; lin=false; }
else { win=false; mac=false; lin=true; }
//djochange - added the following code to find Konqueror
if (typeof navigator.vendor!="undefined"){
if (navigator.vendor =="KDE") {
ie=false;
ie4=false;
ie5=false;
konqi = true;
ns6=true;
ns4 = false;
exclude = false;

// exclude earlier than kde2.2
var thisKDE=agt;
var splitKDE=thisKDE.split("konqueror/");
var aKDE=splitKDE[1].split("; ");
var KDEn=parseFloat(aKDE[0]);
var oldKde=false;
if(KDEn<2.2){oldKde=true;exclude=true;ns6=false;konqi=false;}
}
}
//end djochange
// differentiate between opera 5 and 6
var op6=false;
if(op5){
if((agt.indexOf("opera 6")!=-1)||(agt.indexOf("opera/6")!=-1)){op6=true;op5=false;}
}
var op7=false;
if(op5 || op6){
if((agt.indexOf("opera 7")!=-1)||(agt.indexOf("opera/7")!=-1)){op7=true;op5=false;op6=false;}
}


// find ie5.5 and ie6
var ie55=false;
var ie6 =false;
var ie7 =false;
if(ie5&&agt.indexOf("msie 5.5")!=-1) { ie55=true; }
if(ie5&&agt.indexOf("msie 6")!=-1)   { ie55=true; ie6=true; }
if(ie5&&agt.indexOf("msie 7")!=-1)   { ie55=true; ie6=true; ie7=true; }

// safari
var safari=false;
if(agt.indexOf("safari")!=-1) { safari = true; }
var firefox=false;
if(agt.indexOf("firefox")!=-1) { firefox = true; }



