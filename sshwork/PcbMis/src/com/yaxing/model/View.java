package com.yaxing.model;

/**
 * View entity. @author MyEclipse Persistence Tools
 */

public class View implements java.io.Serializable {

	// Fields

	private ViewId id;

	// Constructors

	/** default constructor */
	public View() {
	}

	/** full constructor */
	public View(ViewId id) {
		this.id = id;
	}

	// Property accessors

	public ViewId getId() {
		return this.id;
	}

	public void setId(ViewId id) {
		this.id = id;
	}

}