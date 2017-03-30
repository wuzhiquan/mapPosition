package com.koron.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koron.web.bean.TrackBean;

public interface TrackMapper {
	/**
	 * 添加轨迹
	 * @return
	 */
	public Integer addTrack(TrackBean bean);
	/**
	 * 获取某个人的轨迹
	 * @param userId 用户ID
	 * @return
	 */
	public List<TrackBean> list(@Param("userid")String userId);
	
}
