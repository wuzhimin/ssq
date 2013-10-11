package com.yaxing.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.yaxing.Service.ViewManager;
import com.yaxing.model.View;

public class ViewAction extends ActionSupport {
	private ViewManager viewManager;
	private List viewList;
	private Long id;
	public List getViewList() {
		return viewList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setViewList(List viewList) {
		this.viewList = viewList;
	}

	public void setViewManager(ViewManager viewManager) {
		this.viewManager = viewManager;
	}
	public String query() {
		 
		System.out.println(id);
		viewList = viewManager.getView(id);
		return SUCCESS;
	}
	public String queryList() {
		viewList = viewManager.getViewList();
		System.out.println();
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

}
