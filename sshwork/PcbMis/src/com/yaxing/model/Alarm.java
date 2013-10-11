package com.yaxing.model;

import java.sql.Timestamp;

/**
 * Alarm entity. @author MyEclipse Persistence Tools
 */

public class Alarm implements java.io.Serializable {

	// Fields

	private Integer wid;
	private String wcontent;
	private Timestamp wtime;
	private Integer counts;
	private Integer gaptime;
	private String state;
	private String uno;

	// Constructors

	/** default constructor */
	public Alarm() {
	}

	/** full constructor */
	public Alarm(String wcontent, Timestamp wtime, Integer counts,
			Integer gaptime, String state, String uno) {
		this.wcontent = wcontent;
		this.wtime = wtime;
		this.counts = counts;
		this.gaptime = gaptime;
		this.state = state;
		this.uno = uno;
	}

	// Property accessors

	public Integer getWid() {
		return this.wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getWcontent() {
		return this.wcontent;
	}

	public void setWcontent(String wcontent) {
		this.wcontent = wcontent;
	}

	public Timestamp getWtime() {
		return this.wtime;
	}

	public void setWtime(Timestamp wtime) {
		this.wtime = wtime;
	}

	public Integer getCounts() {
		return this.counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public Integer getGaptime() {
		return this.gaptime;
	}

	public void setGaptime(Integer gaptime) {
		this.gaptime = gaptime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUno() {
		return this.uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

}