package com.bookma.struts.form;

import org.apache.struts.action.ActionForm;

import com.Bookinfo;

public class BookmaForm extends ActionForm {
	private Bookinfo bookinfoobj = new Bookinfo();

	public Bookinfo getBookinfoobj() {
		return bookinfoobj;
	}

	public void setBookinfoobj(Bookinfo bookinfoobj) {
		this.bookinfoobj = bookinfoobj;
	}

	
}
