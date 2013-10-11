package com.yaxing.Dao;

import java.util.List;

import com.yaxing.model.View;

public interface ViewDao {
	public List<View> getView(Long id);

	public List<View> getViewList();

}
