package com.yaxing.model;

import java.sql.Timestamp;

/**
 * ViewId entity. @author MyEclipse Persistence Tools
 */

public class ViewId implements java.io.Serializable {

	// Fields

	private Long id;
	private Integer wid;
	private String name;
	private Integer pid;
	private String wcontent;
	private Timestamp wtime;
	private Integer counts;

	// Constructors

	/** default constructor */
	public ViewId() {
	}

	/** minimal constructor */
	public ViewId(Long id, Integer wid, String wcontent, Timestamp wtime,
			Integer counts) {
		this.id = id;
		this.wid = wid;
		this.wcontent = wcontent;
		this.wtime = wtime;
		this.counts = counts;
	}

	/** full constructor */
	public ViewId(Long id, Integer wid, String name, Integer pid,
			String wcontent, Timestamp wtime, Integer counts) {
		this.id = id;
		this.wid = wid;
		this.name = name;
		this.pid = pid;
		this.wcontent = wcontent;
		this.wtime = wtime;
		this.counts = counts;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWid() {
		return this.wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewId))
			return false;
		ViewId castOther = (ViewId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getWid() == castOther.getWid()) || (this.getWid() != null
						&& castOther.getWid() != null && this.getWid().equals(
						castOther.getWid())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getPid() == castOther.getPid()) || (this.getPid() != null
						&& castOther.getPid() != null && this.getPid().equals(
						castOther.getPid())))
				&& ((this.getWcontent() == castOther.getWcontent()) || (this
						.getWcontent() != null
						&& castOther.getWcontent() != null && this
						.getWcontent().equals(castOther.getWcontent())))
				&& ((this.getWtime() == castOther.getWtime()) || (this
						.getWtime() != null
						&& castOther.getWtime() != null && this.getWtime()
						.equals(castOther.getWtime())))
				&& ((this.getCounts() == castOther.getCounts()) || (this
						.getCounts() != null
						&& castOther.getCounts() != null && this.getCounts()
						.equals(castOther.getCounts())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getWid() == null ? 0 : this.getWid().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		result = 37 * result
				+ (getWcontent() == null ? 0 : this.getWcontent().hashCode());
		result = 37 * result
				+ (getWtime() == null ? 0 : this.getWtime().hashCode());
		result = 37 * result
				+ (getCounts() == null ? 0 : this.getCounts().hashCode());
		return result;
	}

}