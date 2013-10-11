package com.wzm.server.service.formula;

import java.io.IOException;
import java.io.Serializable;

import com.wzm.server.service.base.BaseService;


public interface FormulaService extends BaseService {
	
	/**
	 * 初始化所有红球公式，将红球公式从文件存储到数据库
	 * @throws IOException 
	 */
	public void initAllRedFormula() throws IOException;
	

	/**
	 * 增加一个红球公式
	 * @param name
	 * @param formulaValue
	 * @param desc
	 */
	public Serializable addARedFormula(String name, String formulaValue,String desc);
	
	
	public void caclAllRedFormula() throws Exception;
	
	public void caclARedFormula(String formulaName);
	
	public void caclAllRedFormulaByNow();
	
	public void caclARedFormulaByNow(String formulaName);
	
	public void verifyAllRedFormula() throws Exception;

	/**
	 * 生成所有红球公式计算值正确性多期统计表
	 * @param spaceNum
	 * @throws Exception
	 */
	public void writeAllRedFormulaCaclVerifyMulStats(int spaceNum) throws Exception;


	void writeAllRedFormulaCaclVerifyMulForcast(int spaceNum, int forcastSpaceNum) throws Exception;


	/**
	 * 验证根据红球公式计算值正确性多期统计表的数据来预测未来红球
	 * @param spaceNum
	 * @param forcastSpaceNum
	 */
	void verifyAllRedFormulaCaclVerifyMulForcast(int spaceNum, int forcastSpaceNum);
	
	
}
