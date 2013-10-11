package com.doginfo.struts.form;

import org.apache.struts.action.ActionForm;

import com.doginfo.struts.obj.DoginfoObj;

public class DoginfoForm extends ActionForm{
    private DoginfoObj obj = new DoginfoObj();

	public DoginfoObj getObj() {
		return obj;
	}

	public void setObj(DoginfoObj obj) {
		this.obj = obj;
	}
}
