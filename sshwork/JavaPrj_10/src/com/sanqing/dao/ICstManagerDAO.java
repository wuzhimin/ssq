package com.sanqing.dao;

import java.util.List;
import java.util.Map;

import com.sanqing.po.CstManager;
import com.sanqing.util.PageResult;

public interface ICstManagerDAO {

	public abstract PageResult findAll(Map paramMap);
	public abstract List<CstManager> findByManName(String manName);
}