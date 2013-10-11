package com.wzm.server.entity.ssq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;
import com.wzm.util.SsqConstant;

/**
 * 
 * @author wzm 类说明：双色球红球尾数统计表
 * 
 */

@Entity
@Table(name = "t_ssqtailstats", catalog = "ssq")
public class SsqTailStats extends BaseEntity implements StatsCompute {

	private static final long serialVersionUID = 1664123111750852101L;

	private SsqRecord record; // 双色球开奖记录

	private int ssqIndex; // 双色球开奖期号
	
	private int t0count;   // 尾数0个数
	private int t1count;   // 尾数1个数
	private int t2count;   // 尾数2个数
	private int t3count;   // 尾数3个数
	private int t4count;   // 尾数4个数
	private int t5count;   // 尾数5个数
	private int t6count;   // 尾数6个数
	private int t7count;   // 尾数7个数
	private int t8count;   // 尾数8个数
	private int t9count;   // 尾数9个数
	
	private boolean hasMulSameTail = false;   // 是否有多个相同尾数，即某个尾数个数大于1
	
	private int tailSum;   // 尾数和
	private boolean tailSumIsOdd = false;   // 尾数和是否奇数
	private boolean tailSumIsPrime = false;   // 尾数和是否质数
	
	public SsqTailStats() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(SsqRecord aRecord) {

		setRecord(aRecord);
		
		int r1 = record.getR1();

		int r2 = record.getR2();

		int r3 = record.getR3();

		int r4 = record.getR4();

		int r5 = record.getR5();

		int r6 = record.getR6();

		ssqIndex = record.getSsqIndex();
		
		int tmp[] = new int[10];
		
		int a = r1 % 10;
		tailSum = tailSum + a;
		tmp[a]++;
		
		a = r2 % 10;
		tailSum = tailSum + a;
		tmp[a]++;
		
		a = r3 % 10;
		tailSum = tailSum + a;
		tmp[a]++;
		
		a = r4 % 10;
		tailSum = tailSum + a;
		tmp[a]++;
		
		a = r5 % 10;
		tailSum = tailSum + a;
		tmp[a]++;
		
		a = r6 % 10;
		tailSum = tailSum + a;
		tmp[a]++;
		
		t0count=tmp[0];
		t1count=tmp[1];
		t2count=tmp[2];
		t3count=tmp[3];
		t4count=tmp[4];
		t5count=tmp[5];
		t6count=tmp[6];
		t7count=tmp[7];
		t8count=tmp[8];
		t9count=tmp[9];
		
		for(int i=0;i<tmp.length;i++) {
			if(tmp[i]>1) {
				hasMulSameTail = true;
				break;
			}
		}

		tailSumIsOdd = tailSum % 2 == 1;
		
		tailSumIsPrime = SsqConstant.PRIME_NUMBER_MAP.containsKey(tailSum)?true:false;
		
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

	@Column(name = "ft0count", nullable = false)
	public int getT0count() {
		return t0count;
	}

	public void setT0count(int t0count) {
		this.t0count = t0count;
	}

	@Column(name = "ft1count", nullable = false)
	public int getT1count() {
		return t1count;
	}

	public void setT1count(int t1count) {
		this.t1count = t1count;
	}

	@Column(name = "ft2count", nullable = false)
	public int getT2count() {
		return t2count;
	}

	public void setT2count(int t2count) {
		this.t2count = t2count;
	}

	@Column(name = "ft3count", nullable = false)
	public int getT3count() {
		return t3count;
	}

	public void setT3count(int t3count) {
		this.t3count = t3count;
	}

	@Column(name = "ft4count", nullable = false)
	public int getT4count() {
		return t4count;
	}

	public void setT4count(int t4count) {
		this.t4count = t4count;
	}

	@Column(name = "ft5count", nullable = false)
	public int getT5count() {
		return t5count;
	}

	public void setT5count(int t5count) {
		this.t5count = t5count;
	}

	@Column(name = "ft6count", nullable = false)
	public int getT6count() {
		return t6count;
	}

	public void setT6count(int t6count) {
		this.t6count = t6count;
	}

	@Column(name = "ft7count", nullable = false)
	public int getT7count() {
		return t7count;
	}

	public void setT7count(int t7count) {
		this.t7count = t7count;
	}

	@Column(name = "ft8count", nullable = false)
	public int getT8count() {
		return t8count;
	}

	public void setT8count(int t8count) {
		this.t8count = t8count;
	}

	@Column(name = "ft9count", nullable = false)
	public int getT9count() {
		return t9count;
	}

	public void setT9count(int t9count) {
		this.t9count = t9count;
	}

	@Column(name = "fhasmulsametail", nullable = false)
	public boolean isHasMulSameTail() {
		return hasMulSameTail;
	}

	public void setHasMulSameTail(boolean hasMulSameTail) {
		this.hasMulSameTail = hasMulSameTail;
	}

	@Column(name = "ftailsum", nullable = false)
	public int getTailSum() {
		return tailSum;
	}

	public void setTailSum(int tailSum) {
		this.tailSum = tailSum;
	}

	@Column(name = "ftailsumisodd", nullable = false)
	public boolean isTailSumIsOdd() {
		return tailSumIsOdd;
	}

	public void setTailSumIsOdd(boolean tailSumIsOdd) {
		this.tailSumIsOdd = tailSumIsOdd;
	}

	@Column(name = "ftailsumisprime", nullable = false)
	public boolean isTailSumIsPrime() {
		return tailSumIsPrime;
	}

	public void setTailSumIsPrime(boolean tailSumIsPrime) {
		this.tailSumIsPrime = tailSumIsPrime;
	}
}
