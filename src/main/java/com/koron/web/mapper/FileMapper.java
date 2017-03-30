package com.koron.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koron.web.bean.FileBean;
import com.koron.web.bean.query.FileQueryBean;



public interface FileMapper {
	/** 获取所有文件 */
	public List<FileBean> listFile(FileQueryBean bean);

	/**
	 * 添加一种文件
	 */
	public int addFile(FileBean bean);

	/** 删除文件 */
	public Integer deleteFile(@Param("id") int id);

	/**
	 * 修改文件
	 */
	public Integer updateFile(FileBean bean);

	/**
	 * 查询文件条数
	 */
	public Integer listFileCount(FileQueryBean bean);
	/**
	 * 根据id查询文件
	 * @param id
	 * @return
	 */
	public FileBean getFileById(@Param("id")int id);
	/**
	 * 根据key查询文件
	 * @param key
	 * @return
	 */
	public FileBean getFileByKey(@Param("key")String key);
	/**
	 * 根据key模糊查询文件数量
	 * @param key
	 * @return
	 */
	public int getFileCountByKey(@Param("key")String key);
	/**
	 * 清空key
	 * @param key
	 * @return
	 */
	public int clearFileKey(@Param("key")String key);
	/**
	 * 被引用次数加1
	 * @param id
	 * @return
	 */
	public int addNum(@Param("id")Integer id);

}