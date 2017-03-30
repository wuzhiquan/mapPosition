package com.koron.web.mapper;

import org.apache.ibatis.annotations.Param;

import com.koron.web.bean.CaseInfoBean;

public interface CaseInfoMapper {
	public CaseInfoBean getCaseInfoById(@Param("id")Integer id);
}
