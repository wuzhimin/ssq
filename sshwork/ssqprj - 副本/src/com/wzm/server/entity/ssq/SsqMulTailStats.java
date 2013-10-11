package com.wzm.server.entity.ssq;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球多期红球尾数统计表
 * 
 */

@Entity
@Table(name = "t_ssqmultailstats", catalog = "ssq")
public class SsqMulTailStats extends BaseEntity implements MulStatsCompute {

	private static final long serialVersionUID = 1874123111750852101L;

	private List<StatsCompute> records; // 双色球红球尾数统计记录

	private int fromSsqIndex; // 开始双色球开奖期号
	private int toSsqIndex; // 结束双色球开奖期号

	private int spaceNum; // 间隔期数

	private int t0count; // 尾数0个数
	private int t1count; // 尾数1个数
	private int t2count; // 尾数2个数
	private int t3count; // 尾数3个数
	private int t4count; // 尾数4个数
	private int t5count; // 尾数5个数
	private int t6count; // 尾数6个数
	private int t7count; // 尾数7个数
	private int t8count; // 尾数8个数
	private int t9count; // 尾数9个数

	public SsqMulTailStats() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(List<StatsCompute> records) {
		if (!(records.get(0) instanceof SsqTailStats)) {
			return;
		}

		int size = records.size();

		setFromSsqIndex(((SsqTailStats) records.get(0)).getSsqIndex());

		setToSsqIndex(((SsqTailStats) records.get(size - 1)).getSsqIndex());

		setSpaceNum(size);

		for (int j = 0; j < size; j++) {
			setT0count(getT0count()
					+ ((SsqTailStats) records.get(j)).getT0count());
			setT1count(getT1count()
					+ ((SsqTailStats) records.get(j)).getT1count());
			setT2count(getT2count()
					+ ((SsqTailStats) records.get(j)).getT2count());
			setT3count(getT3count()
					+ ((SsqTailStats) records.get(j)).getT3count());
			setT4count(getT4count()
					+ ((SsqTailStats) records.get(j)).getT4count());
			setT5count(getT5count()
					+ ((SsqTailStats) records.get(j)).getT5count());
			setT6count(getT6count()
					+ ((SsqTailStats) records.get(j)).getT6count());
			setT7count(getT7count()
					+ ((SsqTailStats) records.get(j)).getT7count());
			setT8count(getT8count()
					+ ((SsqTailStats) records.get(j)).getT8count());
			setT9count(getT9count()
					+ ((SsqTailStats) records.get(j)).getT9count());
		}

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

	@Transient
	public List<StatsCompute> getRecords() {
		return records;
	}

	public void setRecords(List<StatsCompute> records) {
		this.records = records;
	}

	@Column(name = "ffromssqindex", nullable = false)
	public int getFromSsqIndex() {
		return fromSsqIndex;
	}

	public void setFromSsqIndex(int fromSsqIndex) {
		this.fromSsqIndex = fromSsqIndex;
	}

	@Column(name = "ftossqindex", nullable = false)
	public int getToSsqIndex() {
		return toSsqIndex;
	}

	public void setToSsqIndex(int toSsqIndex) {
		this.toSsqIndex = toSsqIndex;
	}

	@Column(name = "fspacenum", nullable = false)
	public int getSpaceNum() {
		return spaceNum;
	}

	public void setSpaceNum(int spaceNum) {
		this.spaceNum = spaceNum;
	}
}
