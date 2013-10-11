package com.wzm.server.entity.formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm
 * 类说明：红球公式
 *
 */

@Entity
@Table(name="t_redformula",catalog="ssq")
public class RedFormula extends BaseEntity {


	private static final long serialVersionUID = -912282363890508644L;

	public RedFormula() {
		
	}
	
	private String name;       // 公式名称
	
	private String value;      // 公式值
	
	private String desc;      // 公式描述
	 
	
	@Column(name = "fvalue", nullable=false, unique = true)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "fdesc", nullable=true)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "fname", nullable=false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
