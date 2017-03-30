package com.koron.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koron.web.bean.CaseBean;

public interface CaseMapper {
	public List<CaseBean> getAll();
	public Integer addCase(CaseBean bean);
	public CaseBean getCaseById(@Param("id")Integer id);
	public Integer updateCase(CaseBean bean);
}
