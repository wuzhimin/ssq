package com.wzm.server.entity.ssq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm
 * 类说明：双色球开奖记录
 *
 */

@Entity
@Table(name="t_ssqrecord",catalog="ssq")
public class SsqRecord extends BaseEntity {


	private static final long serialVersionUID = -926282363890508644L;

	public SsqRecord() {
		
	}
	
	private int ssqIndex;      // 双色球开奖期号
	
	private int nextSsqIndex;      // 双色球下期开奖期号
	 
	private int r1;       // 红球1
	
	private int r2;       // 红球2
	
	private int r3;       // 红球3
	
	private int r4;       // 红球4
	
	private int r5;       // 红球5
	
	private int r6;       // 红球6
	
	private int b1;       // 篮球1
	
	@Column(name = "fssqindex", unique = true, nullable=false)
	public int getSsqIndex() {
		return ssqIndex;
	}

	public void setSsqIndex(int ssqIndex) {
		this.ssqIndex = ssqIndex;
	}

	@Column(name = "fr1", nullable=false)
	public int getR1() {
		return r1;
	}

	public void setR1(int r1) {
		this.r1 = r1;
	}

	@Column(name = "fr2", nullable=false)
	public int getR2() {
		return r2;
	}

	public void setR2(int r2) {
		this.r2 = r2;
	}

	@Column(name = "fr3", nullable=false)
	public int getR3() {
		return r3;
	}

	public void setR3(int r3) {
		this.r3 = r3;
	}

	@Column(name = "fr4", nullable=false)
	public int getR4() {
		return r4;
	}

	public void setR4(int r4) {
		this.r4 = r4;
	}

	@Column(name = "fr5", nullable=false)
	public int getR5() {
		return r5;
	}

	public void setR5(int r5) {
		this.r5 = r5;
	}

	@Column(name = "fr6", nullable=false)
	public int getR6() {
		return r6;
	}

	public void setR6(int r6) {
		this.r6 = r6;
	}

	@Column(name = "fb1", nullable=false)
	public int getB1() {
		return b1;
	}

	public void setB1(int b1) {
		this.b1 = b1;
	}

	@Column(name = "fnextSsqIndex", nullable=true)
	public int getNextSsqIndex() {
		return nextSsqIndex;
	}

	public void setNextSsqIndex(int nextSsqIndex) {
		this.nextSsqIndex = nextSsqIndex;
	}
	
	public String toString() {
		return getSsqIndex()+ " " + getR1()+" "+ getR2()+" "+ getR3()+" "+ getR4()+" "+ getR5()+" "+ getR6() + " "+ b1;
	}
	
	public String redString() {
		return getSsqIndex()+ " " + getR1()+" "+ getR2()+" "+ getR3()+" "+ getR4()+" "+ getR5()+" "+ getR6();
	}
	
}
