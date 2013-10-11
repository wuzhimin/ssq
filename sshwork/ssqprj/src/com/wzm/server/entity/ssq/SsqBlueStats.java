package com.wzm.server.entity.ssq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球篮球统计表
 * 
 */

@Entity
@Table(name = "t_ssqbluestats", catalog = "ssq")
public class SsqBlueStats extends BaseEntity implements StatsCompute {

	private static final long serialVersionUID = 1664723111750852101L;

	private SsqRecord record; // 双色球开奖记录

	private int ssqIndex; // 双色球开奖期号

	private boolean isOdd; // 蓝球是否奇数

	private boolean isBig; // 篮球是否大数

	private int foruScope; // 篮球4区

	private boolean isPrime; // 篮球是否质数

	private int b1; // 蓝球（冗余数据）

	public SsqBlueStats() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(SsqRecord aRecord) {

		setRecord(aRecord);
		
		b1 = record.getB1();

		ssqIndex = record.getSsqIndex();

		isOdd = b1 % 2 == 1;
		
		isBig = b1 > 8;
		
		foruScope = b1<=4?1:( (b1>4 && b1<=8) ?2:((b1>8 && b1<=12) ?3:4));
		
		isPrime = SsqPrimeStats.PRIME_NUM_MAP.containsKey(b1)?true:false;
		
	}

	@Column(name = "fssqindex", unique = true, nullable = false)
	public int getSsqIndex() {
		return ssqIndex;
	}

	public void setSsqIndex(int ssqIndex) {
		this.ssqIndex = ssqIndex;
	}

	@Transient
	public SsqRecord getRecord() {
		return record;
	}

	public void setRecord(SsqRecord record) {
		this.record = record;
	}

	@Column(name = "fisodd", nullable = false)
	public boolean isOdd() {
		return isOdd;
	}

	public void setOdd(boolean isOdd) {
		this.isOdd = isOdd;
	}

	@Column(name = "fisbig", nullable = false)
	public boolean isBig() {
		return isBig;
	}

	public void setBig(boolean isBig) {
		this.isBig = isBig;
	}

	@Column(name = "ffourscope", nullable = false)
	public int getForuScope() {
		return foruScope;
	}

	public void setForuScope(int foruScope) {
		this.foruScope = foruScope;
	}

	@Column(name = "fisprime", nullable = false)
	public boolean isPrime() {
		return isPrime;
	}

	public void setPrime(boolean isPrime) {
		this.isPrime = isPrime;
	}

	@Column(name = "fb1", nullable = false)
	public int getB1() {
		return b1;
	}

	public void setB1(int b1) {
		this.b1 = b1;
	}

}
