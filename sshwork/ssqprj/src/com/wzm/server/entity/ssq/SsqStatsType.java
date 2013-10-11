package com.wzm.server.entity.ssq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球统计类型表
 * 
 */

@Entity
@Table(name = "t_ssqstatstype", catalog = "ssq")
public class SsqStatsType extends BaseEntity {

	private static final long serialVersionUID = 3428361836054646169L;

	private String name; // 类型名称

	private String desc; // 描述
	
	private String statsEntityClassName;    // 统计信息实体类名称
	
	private String dependedStatsEntityClassName;    // 依赖的统计信息实体类名称
	

	public SsqStatsType() {

	}

	@Column(name = "fname", length = 30, unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "fdesc", length = 50, nullable = false)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "fStatsEntClsName", length = 100, nullable = false)
	public String getStatsEntityClassName() {
		return statsEntityClassName;
	}

	public void setStatsEntityClassName(String statsEntityClassName) {
		this.statsEntityClassName = statsEntityClassName;
	}

	@Column(name = "fdpStatsEntClsName", length = 100, nullable = true)
	public String getDependedStatsEntityClassName() {
		return dependedStatsEntityClassName;
	}

	public void setDependedStatsEntityClassName(
			String dependedStatsEntityClassName) {
		this.dependedStatsEntityClassName = dependedStatsEntityClassName;
	}

}
