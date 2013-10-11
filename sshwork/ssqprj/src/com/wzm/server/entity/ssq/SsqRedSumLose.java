package com.wzm.server.entity.ssq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm
 * 类说明：双色球和值遗漏、连续规律
 *
 */

@Entity
@Table(name="t_ssqredsumlose",catalog="ssq")
public class SsqRedSumLose extends BaseEntity {

	private static final long serialVersionUID = -15864777065538674L;

	public SsqRedSumLose() {
		
	}
	
	private int ssqIndex;      // 双色球当前开奖期号
	
	private int maxLoseCount;       // 最大遗漏期数
	
	private int maxLoseSsqIndex;       // 最大遗漏开始期
	
	private int minLoseCount;       // 最小遗漏期数
	
	private int minLoseSsqIndex;       // 最小遗漏开始期
	
	private int maxContinueCount;       // 最大连续期数
	
	private int maxContinueSsqIndex;       // 最大连续开始期
	
	private int currentLoseCount;         // 当前遗漏期数
	
	private int currentContinueCount;         // 当前连续期数
	
	private String sumType;       			// 和值类型
	
	@Column(name = "fssqindex", unique = true, nullable=false)
	public int getSsqIndex() {
		return ssqIndex;
	}

	public void setSsqIndex(int ssqIndex) {
		this.ssqIndex = ssqIndex;
	}

	@Column(name = "FMaxLoseCount", nullable=true)
	public int getMaxLoseCount() {
		return maxLoseCount;
	}

	public void setMaxLoseCount(int maxLoseCount) {
		this.maxLoseCount = maxLoseCount;
	}

	@Column(name = "FMaxLoseSsqIndex", nullable=true)
	public int getMaxLoseSsqIndex() {
		return maxLoseSsqIndex;
	}

	public void setMaxLoseSsqIndex(int maxLoseSsqIndex) {
		this.maxLoseSsqIndex = maxLoseSsqIndex;
	}

	@Column(name = "FMinLoseCount", nullable=true)
	public int getMinLoseCount() {
		return minLoseCount;
	}

	public void setMinLoseCount(int minLoseCount) {
		this.minLoseCount = minLoseCount;
	}

	@Column(name = "FMinLoseSsqIndex", nullable=true)
	public int getMinLoseSsqIndex() {
		return minLoseSsqIndex;
	}

	public void setMinLoseSsqIndex(int minLoseSsqIndex) {
		this.minLoseSsqIndex = minLoseSsqIndex;
	}

	@Column(name = "FMaxContinueCount", nullable=true)
	public int getMaxContinueCount() {
		return maxContinueCount;
	}

	public void setMaxContinueCount(int maxContinueCount) {
		this.maxContinueCount = maxContinueCount;
	}

	@Column(name = "FMaxContinueSsqIndex", nullable=true)
	public int getMaxContinueSsqIndex() {
		return maxContinueSsqIndex;
	}

	public void setMaxContinueSsqIndex(int maxContinueSsqIndex) {
		this.maxContinueSsqIndex = maxContinueSsqIndex;
	}

	@Column(name = "FCurrentLoseCount", nullable=true)
	public int getCurrentLoseCount() {
		return currentLoseCount;
	}

	public void setCurrentLoseCount(int currentLoseCount) {
		this.currentLoseCount = currentLoseCount;
	}

	@Column(name = "FCurrentContinueCount", nullable=true)
	public int getCurrentContinueCount() {
		return currentContinueCount;
	}

	public void setCurrentContinueCount(int currentContinueCount) {
		this.currentContinueCount = currentContinueCount;
	}

	@Column(name = "FSumType", nullable=true)
	public String getSumType() {
		return sumType;
	}

	public void setSumType(String sumType) {
		this.sumType = sumType;
	}
	
}
