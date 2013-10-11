package com.wzm.server.service.ssq;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wzm.server.service.base.BaseService;


public interface SsqService extends BaseService {
	
	/**
	 * 根据双色球开奖记录文件，将所有记录保存到数据库中
	 * @param ssqRecordFile
	 */
	public void writeAllSsqRecord(String ssqRecordFile);
	

	/**
	 * 增加一条开奖记录
	 * @param recordStr
	 */
	public Serializable addASsqRecord(String recordStr);
	
	/**
	 * 生成所有双色球基本统计信息
	 */
	public void writeAllSsqBaseStats();
	
	
	/**
	 * 根据双色球开始期和结束期写入基本统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqBaseStats(int fromSsqIndex, int toSsqIndex);
	
	/**
	 * 生成所有双色球篮球统计信息
	 */
	public void writeAllSsqBlueStats();
	
	/**
	 * 根据双色球开始期和结束期写入篮球统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqBlueStats(int fromSsqIndex, int toSsqIndex);
	
	/**
	 * 生成所有双色球红球尾数统计信息
	 */
	public void writeAllSsqTailStats();
	
	/**
	 * 根据双色球开始期和结束期写入红球尾数统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqTailStats(int fromSsqIndex, int toSsqIndex);
	
	/**
	 * 生成所有双色球红球质数统计信息
	 */
	public void writeAllSsqPrimeStats();
	
	/**
	 * 根据双色球开始期和结束期写入红球质数统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqPrimeStats(int fromSsqIndex, int toSsqIndex);
	
	/**
	 * 生成所有双色球红球冷热数统计信息
	 */
	public void writeAllSsqColdHotStats();
	
	/**
	 * 根据双色球开始期和结束期写入红球冷热数统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqColdHotStats(int fromSsqIndex, int toSsqIndex);
	
	/**
	 * 生成所有双色球多期红球尾数统计信息
	 */
	public void writeAllSsqMulTailStats(int spaceNum);
	
	/**
	 * 根据双色球开始期和结束期写入红球质数统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqMulTailStats(int fromSsqIndex, int toSsqIndex, int spaceNum);
	
	/**
	 * 生成所有双色球多期红球冷热数统计信息
	 */
	public void writeAllSsqMulColdHotStats(int spaceNum);
	
	/**
	 * 根据双色球开始期和结束期写入红球冷热数多期统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 */
	public void writeSsqMulColdHotStats(int fromSsqIndex, int toSsqIndex, int spaceNum);
	
	/**
	 * 生成所有双色球多期红球质数统计信息
	 */
	public void writeAllSsqMulPrimeStats(int spaceNum);
	
	/**
	 * 根据双色球开始期和结束期写入红球质数多期统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 */
	public void writeSsqMulPrimeStats(int fromSsqIndex, int toSsqIndex, int spaceNum);
	
	
	/**
	 * 生成所有双色球多期蓝球统计信息
	 */
	public void writeAllSsqMulBlueStats(int spaceNum);
	
	/**
	 * 根据双色球开始期和结束期写入蓝球多期统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 */
	public void writeSsqMulBlueStats(int fromSsqIndex, int toSsqIndex, int spaceNum);
	
	/**
	 * 生成所有双色球红球ac值和散度统计信息
	 */
	public void writeAllSsqAcAndSanduStats();
	
	/**
	 * 根据双色球开始期和结束期写入红球ac值和散度统计信息
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 */
	public void writeSsqAcAndSanduStats(int fromSsqIndex, int toSsqIndex);
	
	/**
	 * 根据双色球记录表写入到目前期数为止，不存在的单期统计信息
	 * @param typeName
	 */
	public void writeNotExistSsqStatsByNow(String typeName);
	
	/**
	 * 根据双色球单期统计信息写入到目前期数为止，不存在的多期统计信息
	 * @param typeName
	 * @param spaceNum
	 */
	public void writeNotExistSsqMulStatsByNow(String typeName, int spaceNum);
	
	
	
	public Map<String, Object[]> testBlue(List<String> formulas);


	public Map<String, Object[]> testRed(List<String> formulas);
}
