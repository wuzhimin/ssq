package com.wzm.server.dao.formula;

import java.util.List;

import com.wzm.server.dao.base.BaseDaoImpl;
import com.wzm.server.entity.formula.RedFormula;
import com.wzm.server.entity.formula.RedFormulaCacl;
import com.wzm.server.entity.formula.RedFormulaCaclVerify;

public class FormulaDaoImpl extends BaseDaoImpl implements FormulaDao {

	@Override
	public List<RedFormula> findRedFormulasByHql(String hql) {
		@SuppressWarnings("unchecked")
		List<RedFormula> result = getHibernateTemplate().find(hql);
		return result;
	}

	@Override
	public List<RedFormula> findRedFormulasByHql(String hql, Object[] params) {
		@SuppressWarnings("unchecked")
		List<RedFormula> result = this.getHibernateTemplate().find(hql,  params);
		return result;
	}

	@Override
	public RedFormula findRedFormulaByName(String name) {
		String hql = " from RedFormula s where s.name = ? ";
		
		@SuppressWarnings("unchecked")
		List<RedFormula> result =  this.getHibernateTemplate()
				.find(hql, new Object[] { name });
		
		if(result.size()==0) {
			return null;
		}
		
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RedFormulaCacl findRedFormulaCaclBySsqIndex(int ssqIndex) {
		String hql = " from RedFormulaCacl s where s.ssqIndex = ? ";
		List<RedFormulaCacl> result =  this.getHibernateTemplate()
				.find(hql, new Object[] { ssqIndex });
		
		if(result.size()==0) {
			return null;
		}
		
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RedFormulaCaclVerify findRedFormulaCaclVerifyBySsqIndex(int ssqIndex) {
		String hql = " from RedFormulaCaclVerify s where s.ssqIndex = ? ";
		List<RedFormulaCaclVerify> result =  this.getHibernateTemplate()
				.find(hql, new Object[] { ssqIndex });
		
		if(result.size()==0) {
			return null;
		}
		
		return result.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RedFormulaCaclVerify findRedFormulaCaclVerifyByTargetSsqIndex(int targetSsqIndex) {
		String hql = " from RedFormulaCaclVerify s where s.targetSsqIndex = ? ";
		List<RedFormulaCaclVerify> result =  this.getHibernateTemplate()
				.find(hql, new Object[] { targetSsqIndex });
		
		if(result.size()==0) {
			return null;
		}
		
		return result.get(0);
	}
}
