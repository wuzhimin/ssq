package com.wzm.server.entity.ssq;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球预测统计表，根据多期红球尾数统计表预测
 * 
 */

@Entity
@Table(name = "t_ssqmultailfst", catalog = "ssq")
public class SsqMulTailForcast extends BaseEntity implements MulStatsCompute {

	private static final long serialVersionUID = 1894123111750852101L;

	private List<MulStatsCompute> records; // 双色球红球多期尾数统计记录

	private int fromSsqIndex; // 开始双色球开奖期号
	private int toSsqIndex; // 结束双色球开奖期号
	
	private int targetSsqIndex;   // 目标开奖期号，即预测的那期双色球
	

	private int spaceNum; // 间隔期数
	
	private int forcastSpaceNum;  // 预测间隔期数

	private int t0MaxCount;   // 尾数0最大个数
	private int t0MinCount=100;   // 尾数0最小个数
	
	private int t1MaxCount;   // 尾数1最大个数
	private int t1MinCount=100;   // 尾数1最小个数
	
	private int t2MaxCount;   // 尾数2最大个数
	private int t2MinCount=100;   // 尾数2最小个数
	
	private int t3MaxCount;   // 尾数3最大个数
	private int t3MinCount=100;   // 尾数3最小个数
	
	private int t4MaxCount;   // 尾数4最大个数
	private int t4MinCount=100;   // 尾数4最小个数
	
	private int t5MaxCount;   // 尾数5最大个数
	private int t5MinCount=100;   // 尾数5最小个数
	
	private int t6MaxCount;   // 尾数6最大个数
	private int t6MinCount=100;   // 尾数6最小个数
	
	private int t7MaxCount;   // 尾数7最大个数
	private int t7MinCount=100;   // 尾数7最小个数
	
	private int t8MaxCount;   // 尾数8最大个数
	private int t8MinCount=100;   // 尾数8最小个数
	
	private int t9MaxCount;   // 尾数9最大个数
	private int t9MinCount=100;   // 尾数9最小个数
	
	private String careNums;   // 关注的尾数
	private String careNumsResult;   // 开奖后，关注的尾数正确性
	
	private String killNums;   // 杀的尾数
	private String killNumsResult;   // 开奖后，杀的尾数正确性
	
	private String certainSelectedNums;   // 定选的尾数
	private String certainSelectedNumsCount;   // 定选的尾数数量（最少几个）
	private String certainSelectedNumsResult;   // 定选的尾数
	
	private String possibleSelectedNums;   // 可选的尾数
	private String possibleSelectedNumsCount;   // 可选的尾数数量（最多几个）
	private String possibleSelectedNumsResult;   // 可选的尾数
	
	private boolean verified = false;
	
	public SsqMulTailForcast() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(List<StatsCompute> records) {
		if (!(records.get(0) instanceof SsqMulTailStats)) {
			return;
		}

		int size = records.size();

		setFromSsqIndex(((SsqMulTailStats) records.get(0)).getFromSsqIndex());

		setToSsqIndex(((SsqMulTailStats) records.get(size - 1)).getToSsqIndex());

		setSpaceNum(((SsqMulTailStats) records.get(0)).getSpaceNum());

		for (int j = 0; j < size; j++) {
			SsqMulTailStats stats = ((SsqMulTailStats)records.get(j));
			
			t0MaxCount = stats.getT0count()>t0MaxCount?stats.getT0count():t0MaxCount;
			t0MinCount = stats.getT0count()<t0MinCount?stats.getT0count():t0MinCount;
			
			t1MaxCount = stats.getT1count()>t1MaxCount?stats.getT1count():t1MaxCount;
			t1MinCount = stats.getT1count()<t1MinCount?stats.getT1count():t1MinCount;
			
			t2MaxCount = stats.getT2count()>t2MaxCount?stats.getT2count():t2MaxCount;
			t2MinCount = stats.getT2count()<t2MinCount?stats.getT2count():t2MinCount;
			
			t3MaxCount = stats.getT3count()>t3MaxCount?stats.getT3count():t3MaxCount;
			t3MinCount = stats.getT3count()<t3MinCount?stats.getT3count():t3MinCount;
			
			t4MaxCount = stats.getT4count()>t4MaxCount?stats.getT4count():t4MaxCount;
			t4MinCount = stats.getT4count()<t4MinCount?stats.getT4count():t4MinCount;
			
			t5MaxCount = stats.getT5count()>t5MaxCount?stats.getT5count():t5MaxCount;
			t5MinCount = stats.getT5count()<t5MinCount?stats.getT5count():t5MinCount;
			
			t6MaxCount = stats.getT6count()>t6MaxCount?stats.getT6count():t6MaxCount;
			t6MinCount = stats.getT6count()<t6MinCount?stats.getT6count():t6MinCount;
			
			t7MaxCount = stats.getT7count()>t7MaxCount?stats.getT7count():t7MaxCount;
			t7MinCount = stats.getT7count()<t7MinCount?stats.getT7count():t7MinCount;
			
			t8MaxCount = stats.getT8count()>t8MaxCount?stats.getT8count():t8MaxCount;
			t8MinCount = stats.getT8count()<t8MinCount?stats.getT8count():t8MinCount;
			
			t9MaxCount = stats.getT9count()>t9MaxCount?stats.getT9count():t9MaxCount;
			t9MinCount = stats.getT9count()<t9MinCount?stats.getT9count():t9MinCount;
			
		}
		
		

	}

	@Transient
	public List<MulStatsCompute> getRecords() {
		return records;
	}

	public void setRecords(List<MulStatsCompute> records) {
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
	
	@Column(name = "ftargetssqindex", nullable = false)
	public int getTargetSsqIndex() {
		return targetSsqIndex;
	}

	public void setTargetSsqIndex(int targetSsqIndex) {
		this.targetSsqIndex = targetSsqIndex;
	}

	@Column(name = "ft0maxcount", nullable = false)
	public int getT0MaxCount() {
		return t0MaxCount;
	}

	public void setT0MaxCount(int t0MaxCount) {
		this.t0MaxCount = t0MaxCount;
	}

	@Column(name = "ft0mincount", nullable = false)
	public int getT0MinCount() {
		return t0MinCount;
	}

	public void setT0MinCount(int t0MinCount) {
		this.t0MinCount = t0MinCount;
	}

	@Column(name = "ft1maxcount", nullable = false)
	public int getT1MaxCount() {
		return t1MaxCount;
	}

	public void setT1MaxCount(int t1MaxCount) {
		this.t1MaxCount = t1MaxCount;
	}

	@Column(name = "ft1mincount", nullable = false)
	public int getT1MinCount() {
		return t1MinCount;
	}

	public void setT1MinCount(int t1MinCount) {
		this.t1MinCount = t1MinCount;
	}

	@Column(name = "ft2maxcount", nullable = false)
	public int getT2MaxCount() {
		return t2MaxCount;
	}

	public void setT2MaxCount(int t2MaxCount) {
		this.t2MaxCount = t2MaxCount;
	}

	@Column(name = "ft2mincount", nullable = false)
	public int getT2MinCount() {
		return t2MinCount;
	}

	public void setT2MinCount(int t2MinCount) {
		this.t2MinCount = t2MinCount;
	}

	@Column(name = "ft3maxcount", nullable = false)
	public int getT3MaxCount() {
		return t3MaxCount;
	}

	public void setT3MaxCount(int t3MaxCount) {
		this.t3MaxCount = t3MaxCount;
	}

	@Column(name = "ft3mincount", nullable = false)
	public int getT3MinCount() {
		return t3MinCount;
	}

	public void setT3MinCount(int t3MinCount) {
		this.t3MinCount = t3MinCount;
	}

	@Column(name = "ft4maxcount", nullable = false)
	public int getT4MaxCount() {
		return t4MaxCount;
	}

	public void setT4MaxCount(int t4MaxCount) {
		this.t4MaxCount = t4MaxCount;
	}

	
	@Column(name = "ft4mincount", nullable = false)
	public int getT4MinCount() {
		return t4MinCount;
	}

	public void setT4MinCount(int t4MinCount) {
		this.t4MinCount = t4MinCount;
	}

	@Column(name = "ft5maxcount", nullable = false)
	public int getT5MaxCount() {
		return t5MaxCount;
	}

	public void setT5MaxCount(int t5MaxCount) {
		this.t5MaxCount = t5MaxCount;
	}

	@Column(name = "ft5mincount", nullable = false)
	public int getT5MinCount() {
		return t5MinCount;
	}

	public void setT5MinCount(int t5MinCount) {
		this.t5MinCount = t5MinCount;
	}

	@Column(name = "ft6maxcount", nullable = false)
	public int getT6MaxCount() {
		return t6MaxCount;
	}

	public void setT6MaxCount(int t6MaxCount) {
		this.t6MaxCount = t6MaxCount;
	}

	@Column(name = "ft6mincount", nullable = false)
	public int getT6MinCount() {
		return t6MinCount;
	}

	public void setT6MinCount(int t6MinCount) {
		this.t6MinCount = t6MinCount;
	}

	@Column(name = "ft7maxcount", nullable = false)
	public int getT7MaxCount() {
		return t7MaxCount;
	}

	public void setT7MaxCount(int t7MaxCount) {
		this.t7MaxCount = t7MaxCount;
	}

	@Column(name = "ft7mincount", nullable = false)
	public int getT7MinCount() {
		return t7MinCount;
	}

	public void setT7MinCount(int t7MinCount) {
		this.t7MinCount = t7MinCount;
	}

	@Column(name = "ft8maxcount", nullable = false)
	public int getT8MaxCount() {
		return t8MaxCount;
	}

	public void setT8MaxCount(int t8MaxCount) {
		this.t8MaxCount = t8MaxCount;
	}

	@Column(name = "ft8mincount", nullable = false)
	public int getT8MinCount() {
		return t8MinCount;
	}

	public void setT8MinCount(int t8MinCount) {
		this.t8MinCount = t8MinCount;
	}

	@Column(name = "ft9maxcount", nullable = false)
	public int getT9MaxCount() {
		return t9MaxCount;
	}

	public void setT9MaxCount(int t9MaxCount) {
		this.t9MaxCount = t9MaxCount;
	}

	@Column(name = "ft9mincount", nullable = false)
	public int getT9MinCount() {
		return t9MinCount;
	}

	public void setT9MinCount(int t9MinCount) {
		this.t9MinCount = t9MinCount;
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

	@Column(name = "fcertainsltnums", nullable = true)
	public String getCertainSelectedNums() {
		return certainSelectedNums;
	}

	public void setCertainSelectedNums(String certainSelectedNums) {
		this.certainSelectedNums = certainSelectedNums;
	}

	@Column(name = "fcertainsltnumscnt", nullable = true)
	public String getCertainSelectedNumsCount() {
		return certainSelectedNumsCount;
	}

	public void setCertainSelectedNumsCount(String certainSelectedNumsCount) {
		this.certainSelectedNumsCount = certainSelectedNumsCount;
	}

	@Column(name = "fcertainsltnumsrst", nullable = true)
	public String getCertainSelectedNumsResult() {
		return certainSelectedNumsResult;
	}

	public void setCertainSelectedNumsResult(String certainSelectedNumsResult) {
		this.certainSelectedNumsResult = certainSelectedNumsResult;
	}

	@Column(name = "fposiblesltnums", nullable = true)
	public String getPossibleSelectedNums() {
		return possibleSelectedNums;
	}

	public void setPossibleSelectedNums(String possibleSelectedNums) {
		this.possibleSelectedNums = possibleSelectedNums;
	}

	@Column(name = "fposiblesltnumscnt", nullable = true)
	public String getPossibleSelectedNumsCount() {
		return possibleSelectedNumsCount;
	}

	public void setPossibleSelectedNumsCount(String possibleSelectedNumsCount) {
		this.possibleSelectedNumsCount = possibleSelectedNumsCount;
	}

	@Column(name = "fposiblesltnumsrst", nullable = true)
	public String getPossibleSelectedNumsResult() {
		return possibleSelectedNumsResult;
	}

	public void setPossibleSelectedNumsResult(String possibleSelectedNumsResult) {
		this.possibleSelectedNumsResult = possibleSelectedNumsResult;
	}

	@Column(name = "fforcastSpcNum", nullable = false)
	public int getForcastSpaceNum() {
		return forcastSpaceNum;
	}

	public void setForcastSpaceNum(int forcastSpaceNum) {
		this.forcastSpaceNum = forcastSpaceNum;
	}

	@Column(name = "fisverified", nullable = false)
	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	
	
}
