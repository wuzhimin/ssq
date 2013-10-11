package com.doginfo.service;

import com.doginfo.struts.dao.DoginfoDAO;
import com.doginfo.struts.obj.DoginfoObj;

public class DoginfoService {
	private DoginfoDAO doginfoDAO;

	
	public DoginfoDAO getDoginfoDAO() {
		return doginfoDAO;
	}


	public void setDoginfoDAO(DoginfoDAO doginfoDAO) {
		this.doginfoDAO = doginfoDAO;
	}


	public void taxInsDoginfoObj(DoginfoObj obj)throws Exception{
		doginfoDAO.insDoginfoObj(obj);
	}
}
