package com.wzm.server.entity.ssq;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wzm.server.entity.base.BaseEntity;

/**
 * 
 * @author wzm 类说明：双色球预测统计表，根据多期红球冷热数统计表预测
 * 
 */

@Entity
@Table(name = "t_ssqmulchfst", catalog = "ssq")
public class SsqMulColdHotForcast extends BaseEntity implements MulStatsCompute {

	private static final long serialVersionUID = 2394123111750852101L;

	private List<MulStatsCompute> records; // 双色球红球多期冷热数统计记录

	private int fromSsqIndex; // 开始双色球开奖期号
	private int toSsqIndex; // 结束双色球开奖期号
	
	private int targetSsqIndex;   // 目标开奖期号，即预测的那期双色球
	

	private int spaceNum; // 间隔期数
	
	private int forcastSpaceNum;  // 预测间隔期数

	private int ch1MaxCount;   // 冷热数1最大个数
	private int ch1MinCount=100;   // 冷热数1最小个数
	
	private int ch2MaxCount;   // 冷热数2最大个数
	private int ch2MinCount=100;   // 冷热数2最小个数
	
	private int ch3MaxCount;   // 冷热数3最大个数
	private int ch3MinCount=100;   // 冷热数3最小个数
	
	private int ch4MaxCount;   // 冷热数4最大个数
	private int ch4MinCount=100;   // 冷热数4最小个数
	
	private int ch5MaxCount;   // 冷热数5最大个数
	private int ch5MinCount=100;   // 冷热数5最小个数
	
	private int ch6MaxCount;   // 冷热数6最大个数
	private int ch6MinCount=100;   // 冷热数6最小个数
	
	private int ch7MaxCount;   // 冷热数7最大个数
	private int ch7MinCount=100;   // 冷热数7最小个数
	
	private int ch8MaxCount;   // 冷热数8最大个数
	private int ch8MinCount=100;   // 冷热数8最小个数
	
	private int ch9MaxCount;   // 冷热数9最大个数
	private int ch9MinCount=100;   // 冷热数9最小个数
	
	private int ch10MaxCount;   // 冷热数10最大个数
	private int ch10MinCount=100;   // 冷热数10最小个数
	
	private int ch11MaxCount;   // 冷热数11最大个数
	private int ch11MinCount=100;   // 冷热数11最小个数
	
	private int ch12MaxCount;   // 冷热数12最大个数
	private int ch12MinCount=100;   // 冷热数12最小个数
	
	private int ch13MaxCount;   // 冷热数13最大个数
	private int ch13MinCount=100;   // 冷热数13最小个数
	
	private int ch14MaxCount;   // 冷热数14最大个数
	private int ch14MinCount=100;   // 冷热数14最小个数
	
	private int ch15MaxCount;   // 冷热数15最大个数
	private int ch15MinCount=100;   // 冷热数15最小个数
	
	private int ch16MaxCount;   // 冷热数16最大个数
	private int ch16MinCount=100;   // 冷热数16最小个数
	
	private int ch17MaxCount;   // 冷热数17最大个数
	private int ch17MinCount=100;   // 冷热数17最小个数
	
	private int ch18MaxCount;   // 冷热数18最大个数
	private int ch18MinCount=100;   // 冷热数18最小个数
	
	private int ch19MaxCount;   // 冷热数19最大个数
	private int ch19MinCount=100;   // 冷热数19最小个数
	
	private int ch20MaxCount;   // 冷热数20最大个数
	private int ch20MinCount=100;   // 冷热数20最小个数
	
	private int ch21MaxCount;   // 冷热数21最大个数
	private int ch21MinCount=100;   // 冷热数21最小个数
	
	private int ch22MaxCount;   // 冷热数22最大个数
	private int ch22MinCount=100;   // 冷热数22最小个数
	
	private int ch23MaxCount;   // 冷热数23最大个数
	private int ch23MinCount=100;   // 冷热数23最小个数
	
	private int ch24MaxCount;   // 冷热数24最大个数
	private int ch24MinCount=100;   // 冷热数24最小个数
	
	private int ch25MaxCount;   // 冷热数25最大个数
	private int ch25MinCount=100;   // 冷热数25最小个数
	
	private int ch26MaxCount;   // 冷热数26最大个数
	private int ch26MinCount=100;   // 冷热数26最小个数
	
	private int ch27MaxCount;   // 冷热数27最大个数
	private int ch27MinCount=100;   // 冷热数27最小个数
	
	private int ch28MaxCount;   // 冷热数28最大个数
	private int ch28MinCount=100;   // 冷热数28最小个数
	
	private int ch29MaxCount;   // 冷热数29最大个数
	private int ch29MinCount=100;   // 冷热数29最小个数
	
	private int ch30MaxCount;   // 冷热数30最大个数
	private int ch30MinCount=100;   // 冷热数30最小个数
	
	private int ch31MaxCount;   // 冷热数31最大个数
	private int ch31MinCount=100;   // 冷热数31最小个数
	
	private int ch32MaxCount;   // 冷热数32最大个数
	private int ch32MinCount=100;   // 冷热数32最小个数
	
	private int ch33MaxCount;   // 冷热数33最大个数
	private int ch33MinCount=100;   // 冷热数33最小个数
	
	private String careNums;   // 关注的冷热数
	private String careNumsResult;   // 开奖后，关注的冷热数正确性
	
	private String killNums;   // 杀的冷热数
	private String killNumsResult;   // 开奖后，杀的冷热数正确性
	
	private String selectNums;   // 定选的冷热数
	private String selectNumsResult;   // 开奖后,定选的冷热数正确性
	
	
	private boolean verified = false;
	
	public SsqMulColdHotForcast() {

	}

	/*
	 * 计算统计值
	 */
	public void buildStats(List<StatsCompute> records) {
		
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

	@Column(name = "fch1maxcount", nullable = false)
	public int getCh1MaxCount() {
		return ch1MaxCount;
	}

	public void setCh1MaxCount(int ch1MaxCount) {
		this.ch1MaxCount = ch1MaxCount;
	}

	@Column(name = "fch1mincount", nullable = false)
	public int getCh1MinCount() {
		return ch1MinCount;
	}

	public void setCh1MinCount(int ch1MinCount) {
		this.ch1MinCount = ch1MinCount;
	}

	@Column(name = "fch2maxcount", nullable = false)
	public int getCh2MaxCount() {
		return ch2MaxCount;
	}

	public void setCh2MaxCount(int ch2MaxCount) {
		this.ch2MaxCount = ch2MaxCount;
	}

	@Column(name = "fch2mincount", nullable = false)
	public int getCh2MinCount() {
		return ch2MinCount;
	}

	public void setCh2MinCount(int ch2MinCount) {
		this.ch2MinCount = ch2MinCount;
	}

	@Column(name = "fch3maxcount", nullable = false)
	public int getCh3MaxCount() {
		return ch3MaxCount;
	}

	public void setCh3MaxCount(int ch3MaxCount) {
		this.ch3MaxCount = ch3MaxCount;
	}

	@Column(name = "fch3mincount", nullable = false)
	public int getCh3MinCount() {
		return ch3MinCount;
	}

	public void setCh3MinCount(int ch3MinCount) {
		this.ch3MinCount = ch3MinCount;
	}

	@Column(name = "fch4maxcount", nullable = false)
	public int getCh4MaxCount() {
		return ch4MaxCount;
	}

	public void setCh4MaxCount(int ch4MaxCount) {
		this.ch4MaxCount = ch4MaxCount;
	}

	@Column(name = "fch4mincount", nullable = false)
	public int getCh4MinCount() {
		return ch4MinCount;
	}

	public void setCh4MinCount(int ch4MinCount) {
		this.ch4MinCount = ch4MinCount;
	}

	@Column(name = "fch5maxcount", nullable = false)
	public int getCh5MaxCount() {
		return ch5MaxCount;
	}

	public void setCh5MaxCount(int ch5MaxCount) {
		this.ch5MaxCount = ch5MaxCount;
	}

	@Column(name = "fch5mincount", nullable = false)
	public int getCh5MinCount() {
		return ch5MinCount;
	}

	public void setCh5MinCount(int ch5MinCount) {
		this.ch5MinCount = ch5MinCount;
	}

	@Column(name = "fch6maxcount", nullable = false)
	public int getCh6MaxCount() {
		return ch6MaxCount;
	}

	public void setCh6MaxCount(int ch6MaxCount) {
		this.ch6MaxCount = ch6MaxCount;
	}

	@Column(name = "fch6mincount", nullable = false)
	public int getCh6MinCount() {
		return ch6MinCount;
	}

	public void setCh6MinCount(int ch6MinCount) {
		this.ch6MinCount = ch6MinCount;
	}

	@Column(name = "fch7maxcount", nullable = false)
	public int getCh7MaxCount() {
		return ch7MaxCount;
	}

	public void setCh7MaxCount(int ch7MaxCount) {
		this.ch7MaxCount = ch7MaxCount;
	}

	@Column(name = "fch7mincount", nullable = false)
	public int getCh7MinCount() {
		return ch7MinCount;
	}

	public void setCh7MinCount(int ch7MinCount) {
		this.ch7MinCount = ch7MinCount;
	}

	@Column(name = "fch8maxcount", nullable = false)
	public int getCh8MaxCount() {
		return ch8MaxCount;
	}

	public void setCh8MaxCount(int ch8MaxCount) {
		this.ch8MaxCount = ch8MaxCount;
	}

	@Column(name = "fch8mincount", nullable = false)
	public int getCh8MinCount() {
		return ch8MinCount;
	}

	public void setCh8MinCount(int ch8MinCount) {
		this.ch8MinCount = ch8MinCount;
	}

	@Column(name = "fch9maxcount", nullable = false)
	public int getCh9MaxCount() {
		return ch9MaxCount;
	}

	public void setCh9MaxCount(int ch9MaxCount) {
		this.ch9MaxCount = ch9MaxCount;
	}

	@Column(name = "fch09mincount", nullable = false)
	public int getCh9MinCount() {
		return ch9MinCount;
	}

	public void setCh9MinCount(int ch9MinCount) {
		this.ch9MinCount = ch9MinCount;
	}

	@Column(name = "fch10maxcount", nullable = false)
	public int getCh10MaxCount() {
		return ch10MaxCount;
	}

	public void setCh10MaxCount(int ch10MaxCount) {
		this.ch10MaxCount = ch10MaxCount;
	}

	@Column(name = "fch10mincount", nullable = false)
	public int getCh10MinCount() {
		return ch10MinCount;
	}

	public void setCh10MinCount(int ch10MinCount) {
		this.ch10MinCount = ch10MinCount;
	}

	@Column(name = "fch11maxcount", nullable = false)
	public int getCh11MaxCount() {
		return ch11MaxCount;
	}

	public void setCh11MaxCount(int ch11MaxCount) {
		this.ch11MaxCount = ch11MaxCount;
	}

	@Column(name = "fch11mincount", nullable = false)
	public int getCh11MinCount() {
		return ch11MinCount;
	}

	public void setCh11MinCount(int ch11MinCount) {
		this.ch11MinCount = ch11MinCount;
	}

	@Column(name = "fch12maxcount", nullable = false)
	public int getCh12MaxCount() {
		return ch12MaxCount;
	}

	public void setCh12MaxCount(int ch12MaxCount) {
		this.ch12MaxCount = ch12MaxCount;
	}

	@Column(name = "fch12mincount", nullable = false)
	public int getCh12MinCount() {
		return ch12MinCount;
	}

	public void setCh12MinCount(int ch12MinCount) {
		this.ch12MinCount = ch12MinCount;
	}

	@Column(name = "fch13maxcount", nullable = false)
	public int getCh13MaxCount() {
		return ch13MaxCount;
	}

	public void setCh13MaxCount(int ch13MaxCount) {
		this.ch13MaxCount = ch13MaxCount;
	}

	@Column(name = "fch13mincount", nullable = false)
	public int getCh13MinCount() {
		return ch13MinCount;
	}

	public void setCh13MinCount(int ch13MinCount) {
		this.ch13MinCount = ch13MinCount;
	}

	@Column(name = "fch14maxcount", nullable = false)
	public int getCh14MaxCount() {
		return ch14MaxCount;
	}

	public void setCh14MaxCount(int ch14MaxCount) {
		this.ch14MaxCount = ch14MaxCount;
	}

	@Column(name = "fch14mincount", nullable = false)
	public int getCh14MinCount() {
		return ch14MinCount;
	}

	public void setCh14MinCount(int ch14MinCount) {
		this.ch14MinCount = ch14MinCount;
	}

	@Column(name = "fch15maxcount", nullable = false)
	public int getCh15MaxCount() {
		return ch15MaxCount;
	}

	public void setCh15MaxCount(int ch15MaxCount) {
		this.ch15MaxCount = ch15MaxCount;
	}

	@Column(name = "fch15mincount", nullable = false)
	public int getCh15MinCount() {
		return ch15MinCount;
	}

	public void setCh15MinCount(int ch15MinCount) {
		this.ch15MinCount = ch15MinCount;
	}

	@Column(name = "fch16maxcount", nullable = false)
	public int getCh16MaxCount() {
		return ch16MaxCount;
	}

	public void setCh16MaxCount(int ch16MaxCount) {
		this.ch16MaxCount = ch16MaxCount;
	}

	@Column(name = "fch16mincount", nullable = false)
	public int getCh16MinCount() {
		return ch16MinCount;
	}

	public void setCh16MinCount(int ch16MinCount) {
		this.ch16MinCount = ch16MinCount;
	}

	@Column(name = "fch17maxcount", nullable = false)
	public int getCh17MaxCount() {
		return ch17MaxCount;
	}

	public void setCh17MaxCount(int ch17MaxCount) {
		this.ch17MaxCount = ch17MaxCount;
	}

	@Column(name = "fch17mincount", nullable = false)
	public int getCh17MinCount() {
		return ch17MinCount;
	}

	public void setCh17MinCount(int ch17MinCount) {
		this.ch17MinCount = ch17MinCount;
	}

	@Column(name = "fch18maxcount", nullable = false)
	public int getCh18MaxCount() {
		return ch18MaxCount;
	}

	public void setCh18MaxCount(int ch18MaxCount) {
		this.ch18MaxCount = ch18MaxCount;
	}

	@Column(name = "fch18mincount", nullable = false)
	public int getCh18MinCount() {
		return ch18MinCount;
	}

	public void setCh18MinCount(int ch18MinCount) {
		this.ch18MinCount = ch18MinCount;
	}

	@Column(name = "fch19maxcount", nullable = false)
	public int getCh19MaxCount() {
		return ch19MaxCount;
	}

	public void setCh19MaxCount(int ch19MaxCount) {
		this.ch19MaxCount = ch19MaxCount;
	}

	@Column(name = "fch19mincount", nullable = false)
	public int getCh19MinCount() {
		return ch19MinCount;
	}

	public void setCh19MinCount(int ch19MinCount) {
		this.ch19MinCount = ch19MinCount;
	}

	@Column(name = "fch20maxcount", nullable = false)
	public int getCh20MaxCount() {
		return ch20MaxCount;
	}

	public void setCh20MaxCount(int ch20MaxCount) {
		this.ch20MaxCount = ch20MaxCount;
	}

	@Column(name = "fch20mincount", nullable = false)
	public int getCh20MinCount() {
		return ch20MinCount;
	}

	public void setCh20MinCount(int ch20MinCount) {
		this.ch20MinCount = ch20MinCount;
	}

	@Column(name = "fch21maxcount", nullable = false)
	public int getCh21MaxCount() {
		return ch21MaxCount;
	}

	public void setCh21MaxCount(int ch21MaxCount) {
		this.ch21MaxCount = ch21MaxCount;
	}

	@Column(name = "fch21mincount", nullable = false)
	public int getCh21MinCount() {
		return ch21MinCount;
	}

	public void setCh21MinCount(int ch21MinCount) {
		this.ch21MinCount = ch21MinCount;
	}

	@Column(name = "fch22maxcount", nullable = false)
	public int getCh22MaxCount() {
		return ch22MaxCount;
	}

	public void setCh22MaxCount(int ch22MaxCount) {
		this.ch22MaxCount = ch22MaxCount;
	}

	@Column(name = "fch22mincount", nullable = false)
	public int getCh22MinCount() {
		return ch22MinCount;
	}

	public void setCh22MinCount(int ch22MinCount) {
		this.ch22MinCount = ch22MinCount;
	}

	@Column(name = "fch23maxcount", nullable = false)
	public int getCh23MaxCount() {
		return ch23MaxCount;
	}

	public void setCh23MaxCount(int ch23MaxCount) {
		this.ch23MaxCount = ch23MaxCount;
	}

	@Column(name = "fch23mincount", nullable = false)
	public int getCh23MinCount() {
		return ch23MinCount;
	}

	public void setCh23MinCount(int ch23MinCount) {
		this.ch23MinCount = ch23MinCount;
	}

	@Column(name = "fch24maxcount", nullable = false)
	public int getCh24MaxCount() {
		return ch24MaxCount;
	}

	public void setCh24MaxCount(int ch24MaxCount) {
		this.ch24MaxCount = ch24MaxCount;
	}

	@Column(name = "fch24mincount", nullable = false)
	public int getCh24MinCount() {
		return ch24MinCount;
	}

	public void setCh24MinCount(int ch24MinCount) {
		this.ch24MinCount = ch24MinCount;
	}

	@Column(name = "fch25maxcount", nullable = false)
	public int getCh25MaxCount() {
		return ch25MaxCount;
	}

	public void setCh25MaxCount(int ch25MaxCount) {
		this.ch25MaxCount = ch25MaxCount;
	}

	@Column(name = "fch25mincount", nullable = false)
	public int getCh25MinCount() {
		return ch25MinCount;
	}

	public void setCh25MinCount(int ch25MinCount) {
		this.ch25MinCount = ch25MinCount;
	}

	@Column(name = "fch26maxcount", nullable = false)
	public int getCh26MaxCount() {
		return ch26MaxCount;
	}

	public void setCh26MaxCount(int ch26MaxCount) {
		this.ch26MaxCount = ch26MaxCount;
	}

	@Column(name = "fch26mincount", nullable = false)
	public int getCh26MinCount() {
		return ch26MinCount;
	}

	public void setCh26MinCount(int ch26MinCount) {
		this.ch26MinCount = ch26MinCount;
	}

	@Column(name = "fch27maxcount", nullable = false)
	public int getCh27MaxCount() {
		return ch27MaxCount;
	}

	public void setCh27MaxCount(int ch27MaxCount) {
		this.ch27MaxCount = ch27MaxCount;
	}

	@Column(name = "fch27mincount", nullable = false)
	public int getCh27MinCount() {
		return ch27MinCount;
	}

	public void setCh27MinCount(int ch27MinCount) {
		this.ch27MinCount = ch27MinCount;
	}

	@Column(name = "fch28maxcount", nullable = false)
	public int getCh28MaxCount() {
		return ch28MaxCount;
	}

	public void setCh28MaxCount(int ch28MaxCount) {
		this.ch28MaxCount = ch28MaxCount;
	}

	@Column(name = "fch28mincount", nullable = false)
	public int getCh28MinCount() {
		return ch28MinCount;
	}

	public void setCh28MinCount(int ch28MinCount) {
		this.ch28MinCount = ch28MinCount;
	}

	@Column(name = "fch29maxcount", nullable = false)
	public int getCh29MaxCount() {
		return ch29MaxCount;
	}

	public void setCh29MaxCount(int ch29MaxCount) {
		this.ch29MaxCount = ch29MaxCount;
	}

	@Column(name = "fch29mincount", nullable = false)
	public int getCh29MinCount() {
		return ch29MinCount;
	}

	public void setCh29MinCount(int ch29MinCount) {
		this.ch29MinCount = ch29MinCount;
	}

	@Column(name = "fch30maxcount", nullable = false)
	public int getCh30MaxCount() {
		return ch30MaxCount;
	}

	public void setCh30MaxCount(int ch30MaxCount) {
		this.ch30MaxCount = ch30MaxCount;
	}

	@Column(name = "fch30mincount", nullable = false)
	public int getCh30MinCount() {
		return ch30MinCount;
	}

	public void setCh30MinCount(int ch30MinCount) {
		this.ch30MinCount = ch30MinCount;
	}

	@Column(name = "fch31maxcount", nullable = false)
	public int getCh31MaxCount() {
		return ch31MaxCount;
	}

	public void setCh31MaxCount(int ch31MaxCount) {
		this.ch31MaxCount = ch31MaxCount;
	}

	@Column(name = "fch31mincount", nullable = false)
	public int getCh31MinCount() {
		return ch31MinCount;
	}

	public void setCh31MinCount(int ch31MinCount) {
		this.ch31MinCount = ch31MinCount;
	}

	@Column(name = "fch32maxcount", nullable = false)
	public int getCh32MaxCount() {
		return ch32MaxCount;
	}

	public void setCh32MaxCount(int ch32MaxCount) {
		this.ch32MaxCount = ch32MaxCount;
	}

	@Column(name = "fch32mincount", nullable = false)
	public int getCh32MinCount() {
		return ch32MinCount;
	}

	public void setCh32MinCount(int ch32MinCount) {
		this.ch32MinCount = ch32MinCount;
	}

	@Column(name = "fch33maxcount", nullable = false)
	public int getCh33MaxCount() {
		return ch33MaxCount;
	}

	public void setCh33MaxCount(int ch33MaxCount) {
		this.ch33MaxCount = ch33MaxCount;
	}

	@Column(name = "fch33mincount", nullable = false)
	public int getCh33MinCount() {
		return ch33MinCount;
	}

	public void setCh33MinCount(int ch33MinCount) {
		this.ch33MinCount = ch33MinCount;
	}

	
	
}
