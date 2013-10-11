package com.wzm.server.entity.formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：红球公式计算值正确性多期预测表
 * 
 */

@Entity
@Table(name = "t_redfulcaclfst", catalog = "ssq")
public class RedFormulaCaclForcast extends BaseEntity {

	private static final long serialVersionUID = -955288653890508744L;

	public RedFormulaCaclForcast() {

	}

	private int fromSsqIndex;     // 开始期
	private int toSsqIndex;       // 结束期
	private int targetSsqIndex;   // 目标开奖期号，即预测的那期双色球

	private int spaceNum; // 间隔期数
	private int forcastSpaceNum;  // 预测间隔期数
	
	private String careNums;   // 关注的
	private String careNumsResult;   // 开奖后，关注的正确性
	
	private String killNums;   // 杀的
	private String killNumsResult;   // 开奖后，杀的正确性
	
	private String selectNums;   // 定选的
	private String selectNumsResult;   // 开奖后,定选的正确性
	
	private boolean verified = false;
	
	
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

	@Column(name = "ftargetssqindex", nullable = false)
	public int getTargetSsqIndex() {
		return targetSsqIndex;
	}

	public void setTargetSsqIndex(int targetSsqIndex) {
		this.targetSsqIndex = targetSsqIndex;
	}

	@Column(name = "fforcastspacenum", nullable = false)
	public int getForcastSpaceNum() {
		return forcastSpaceNum;
	}

	public void setForcastSpaceNum(int forcastSpaceNum) {
		this.forcastSpaceNum = forcastSpaceNum;
	}
	
	@Column(name = "fspacenum", nullable = false)
	public int getSpaceNum() {
		return spaceNum;
	}

	public void setSpaceNum(int spaceNum) {
		this.spaceNum = spaceNum;
	}

	@Column(name = "fcarenums", nullable = true)
	public String getCareNums() {
		return careNums;
	}

	public void setCareNums(String careNums) {
		this.careNums = careNums;
	}

	@Column(name = "fcarenumsrst", nullable = true)
	public String getCareNumsResult() {
		return careNumsResult;
	}

	public void setCareNumsResult(String careNumsResult) {
		this.careNumsResult = careNumsResult;
	}

	@Column(name = "fkillnums", nullable = true)
	public String getKillNums() {
		return killNums;
	}

	public void setKillNums(String killNums) {
		this.killNums = killNums;
	}

	@Column(name = "fkillnumsrst", nullable = true)
	public String getKillNumsResult() {
		return killNumsResult;
	}

	public void setKillNumsResult(String killNumsResult) {
		this.killNumsResult = killNumsResult;
	}

	@Column(name = "fselectnums", nullable = true)
	public String getSelectNums() {
		return selectNums;
	}

	public void setSelectNums(String selectNums) {
		this.selectNums = selectNums;
	}

	@Column(name = "fselectnumsrst", nullable = true)
	public String getSelectNumsResult() {
		return selectNumsResult;
	}

	public void setSelectNumsResult(String selectNumsResult) {
		this.selectNumsResult = selectNumsResult;
	}

	@Column(name = "fverified", nullable = false)
	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}


}
