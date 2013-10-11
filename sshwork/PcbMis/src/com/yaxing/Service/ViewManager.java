package com.yaxing.Service;

import java.util.List;

import com.yaxing.model.View;

public interface ViewManager {
	public List<View> getView(Long id);

	public List<View> getViewList();

}
