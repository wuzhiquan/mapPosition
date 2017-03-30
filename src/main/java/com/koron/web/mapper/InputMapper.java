package com.koron.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.koron.web.bean.PersonBean;

public interface InputMapper {

	/**
	 * 添加人员
	 * @return
	 */
	public Integer addPeople(PersonBean bean);
	
	/**
	 * 更新最后的地址
	 * @param bean
	 * @return
	 */
	public Integer updatePeopleAddress(PersonBean bean);
	
	/**
	 * 获取人员列表
	 * @param bean
	 * @return
	 */
	public 	List<PersonBean> getList();
	
	/**
	 * 判断名字是否存在
	 * @param bean
	 * @return
	 */
	public Integer getCount(@Param("name")String name);
	
	/**
	 * 通过id获取openid
	 * @param bean
	 * @return
	 */
	public String getOpenid(@Param("id")Integer id);
	
	/**
	 * 通过openid获取用户
	 * @param bean
	 * @return
	 */
	public PersonBean getUserByOpenid(@Param("openid")String openid);
	/**
	 * 通过name获取用户
	 * @param name
	 * @return
	 */
	public PersonBean getUserByName(@Param("name")String name);
	/**
	 * 更新上班状态（下班）
	 * @param bean
	 * @return
	 */
	public Integer updateStatus(PersonBean bean);
}
