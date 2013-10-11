package com.yaxing.ServiceImpl;

import java.util.List;

import com.yaxing.Dao.ViewDao;
import com.yaxing.Service.ViewManager;
import com.yaxing.model.View;

public class ViewManagerImpl implements ViewManager {
	private ViewDao viewDao;

	public void setViewDao(ViewDao viewDao) {
		this.viewDao = viewDao;
	}


	public List<View> getView(Long id) {
		// TODO Auto-generated method stub
		return viewDao.getView(id);
	}


	public List<View> getViewList() {
		// TODO Auto-generated method stub
		return viewDao.getViewList();
	}
	
}
