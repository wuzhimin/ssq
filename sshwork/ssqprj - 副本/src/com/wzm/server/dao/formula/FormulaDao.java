package com.wzm.server.dao.formula;

import java.util.List;

import com.wzm.server.dao.base.BaseDao;
import com.wzm.server.entity.formula.RedFormula;
import com.wzm.server.entity.formula.RedFormulaCacl;
import com.wzm.server.entity.formula.RedFormulaCaclVerify;

public interface FormulaDao extends BaseDao {
	
	public List<RedFormula> findRedFormulasByHql(String hql);
	
	public List<RedFormula> findRedFormulasByHql(String hql, Object[] params);
	
	/**
	 * 根据红球公式名称，获取红球公式
	 * @param name
	 * @return
	 */
	public RedFormula findRedFormulaByName(String name);
	
	
	public RedFormulaCacl findRedFormulaCaclBySsqIndex(int ssqIndex);
	
	public RedFormulaCaclVerify findRedFormulaCaclVerifyBySsqIndex(int ssqIndex);
	
	public RedFormulaCaclVerify findRedFormulaCaclVerifyByTargetSsqIndex(int ssqIndex);
	
	
	
}
