package com.laodong.pub.util.base;

import org.apache.struts.action.ActionForm;

public class BaseForm extends ActionForm {
	private int strutsAction = 0;
	private int pagerOffSet = 1;
    private Pager pager = null;
    public int getPagerOffSet() {
        return pagerOffSet;
    }
    public void setPagerOffSet(int pagerOffSet) {
        this.pagerOffSet = pagerOffSet;        
    }
    public Pager getPager(){
      pager = new Pager();
      pager.setLength(20);//设定每页记录数
      pager.setPagerOffSet(pagerOffSet);
      return pager;
    }
	public int getStrutsAction() {
		return strutsAction;
	}
	public void setStrutsAction(int strutsAction) {
		this.strutsAction = strutsAction;
	}
    
}
