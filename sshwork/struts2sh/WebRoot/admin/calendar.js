function showDateForm(d){
  var arg = new Object();
  arg.str_datetime = d;
  arg.time_comp = false;
  var rtn = window.showModalDialog('calendar.htm', arg, 'dialogWidth=210px;dialogHeight=220px;status:no;scroll=no;');
  return (rtn == null ? "" : rtn);

}


function showDateTimeForm(d){
  var arg = new Object();
  arg.str_datetime = d;
  arg.time_comp = true;
  var rtn = window.showModalDialog('calendar.htm', arg, 'dialogWidth=210px;dialogHeight=220px;status:no;scroll=no;');
  return (rtn == null ? "" : rtn);

}