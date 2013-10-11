package com.yaxing.action;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.yaxing.Service.AlarmManager;
import com.yaxing.model.Alarm;
import com.yaxing.utils.PageModel;

public class AlarmAction extends ActionSupport {
	private Alarm alarm;
	private AlarmManager alarmManager;
	private Integer wid;
	private List<Alarm> listAlarm;
	
	public List<Alarm> getListAlarm() {
		return listAlarm;
	}

	public void setListAlarm(List<Alarm> listAlarm) {
		this.listAlarm = listAlarm;
	}



	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public void setAlarmManager(AlarmManager alarmManager) {
		this.alarmManager = alarmManager;
	}

	public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
	public String add(){
		alarmManager.add(alarm);
		return SUCCESS;
	}
	public String query(){
		alarm = alarmManager.getAlarm(wid);
		return SUCCESS;
	}
	public String delete(){
		alarmManager.delete(wid);
		return SUCCESS;
	}
	public String update(){
		alarmManager.update(alarm);
		return SUCCESS;
	}
	public String queryList(){
		listAlarm = new ArrayList<Alarm>();
		listAlarm = alarmManager.getAlarmList();
		System.out.println(listAlarm);
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	/**
	 * 分页
	 * 
	 * */
	 public String pagerTaglib()
	 {
	  int pagesize=3;//每页10条记录
	  int offset=0;
	  HttpServletRequest request = ServletActionContext.getRequest(); 
	  if(request.getParameter("pager.offset")!=null)
	   offset=Integer.parseInt(request.getParameter("pager.offset"));
	  
	  PageModel pm=alarmManager.findAllAlarm(offset, pagesize);
	  request.setAttribute("pm", pm);
	  return "pagertaglib";
	 }



}
