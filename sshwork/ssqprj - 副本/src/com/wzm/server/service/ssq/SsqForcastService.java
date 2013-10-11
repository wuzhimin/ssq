package com.wzm.server.service.ssq;

public interface SsqForcastService {
	
	/**
	 * 根据双色球开始期和结束期及间隔，写入红球尾数预测值
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	public void writeSsqTailForcast(int fromSsqIndex, int toSsqIndex, int spaceNum, int forcastSpaceNum);
	
	/**
	 * 根据双色球开始期和结束期及间隔，验证红球尾数预测值
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	public void verifySsqTailForcast(int fromSsqIndex, int toSsqIndex, int spaceNum, int forcastSpaceNum);
	
	/**
	 * 根据双色球多期尾数统计信息写入到目前期数为止，不存在的多期尾数预测信息
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	public void writeNotExistSsqTailForcast(int spaceNum, int forcastSpaceNum);
	
	/**
	 * 根据双色球多期尾数统计信息，验证到目前期数为止未验证过的红球尾数预测值
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	public void verifyNotExistSsqTailForcast(int spaceNum, int forcastSpaceNum);
	
	
	/**
	 * 根据双色球开始期和结束期写入红球冷热数预测值
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	public void writeSsqChForcast(int fromSsqIndex, int toSsqIndex, int spaceNum, int forcastSpaceNum);
	
	/**
	 * 根据双色球多期冷热数统计信息写入到目前期数为止，不存在的多期冷热数预测信息
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	public void writeNotExistSsqChForcast(int spaceNum, int forcastSpaceNum);
	
	/**
	 * * 根据双色球多期冷热数统计信息，验证到目前期数为止未验证过的红球冷热数预测值
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	public void verifyNotExistSsqCHForcast(int spaceNum, int forcastSpaceNum);
	
	/**
	 * 根据双色球开始期和结束期及间隔，验证红球冷热数预测值，写入数据库
	 * @param fromSsqIndex
	 * @param toSsqIndex
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	public void verifySsqCHForcast(int fromSsqIndex, int toSsqIndex, int spaceNum, int forcastSpaceNum);

}
