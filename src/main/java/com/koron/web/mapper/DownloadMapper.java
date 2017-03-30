package com.koron.web.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.koron.web.bean.DownloadBean;
import com.koron.web.bean.query.DownQueryBean;

public interface DownloadMapper {
	/**
	 * 添加一种文件
	 */
	public int addFile(DownloadBean bean);

	/**
	 * 修改文件描述或公开状态
	 */
	public Integer updateDownloadFile(DownloadBean bean);

	/**
	 * 根据id查询文件
	 * @param id
	 * @return
	 */
	public DownloadBean getFileById(@Param("id")int id);
	/**
	 * 根据条件模糊查询文件
	 * @param key
	 * @return
	 */
	public List<DownloadBean> queryFileList(DownQueryBean bean);
	/**
	 * 条件查询匹配文件数量
	 * @param key
	 * @return
	 */
	
	public int queryFileListCount(DownQueryBean bean);
	/** 修改文件公开状态
	 * @param DownloadBean
	 * @return
	 */
	public int cheangFlag(DownloadBean bean);
	
}