package com.koron.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.koron.ebs.util.field.FieldBean;

import com.koron.web.bean.DefineFieldBean;
import com.koron.web.bean.EnumDBBean;
import com.koron.web.bean.EnumDetailDBBean;
import com.koron.web.bean.LayerBean;

public interface UserDefineMapper {

	public List<DefineFieldBean> getFieldBeans(@Param("layerId") int layerId);
	/**
	 * 根据层ID加名称，获取字段属性
	 * 
	 * @param layerId
	 * @param name
	 * @return
	 */
	public DefineFieldBean getFieldBean(@Param("layerId") int layerId, @Param("name") String name);

	public int removeFieldBean(@Param("id") int id);

	public int addFieldBean(FieldBean bean);

	public int updateFieldBean(FieldBean bean);

	public int addLayer(LayerBean bean);

	/**
	 * 删除层别
	 */
	public int removeLayer(int id);

	public int updateLayer(LayerBean bean);

	/**
	 * 获取某个层下的直属下级，-1为顶级层
	 */
	public List<LayerBean> getLaysers(@Param("groupId") int groupId);

	public LayerBean getLayerBean(@Param("id") int id);

	public LayerBean getLayerBeanByNamePid(@Param("name") String name, @Param("parentId") int parentId);

	/** 新增枚举数据 */
	public int addEnum(EnumDBBean bean);

	/**
	 * 修改枚举
	 */
	public int updateEnum(EnumDBBean bean);

	/**
	 * 删除枚举
	 */
	public int deleteEnum(@Param("id") int id);

	/** 新增枚举项 */
	public int addEnumItem(EnumDetailDBBean bean);

	/** 通过ID删除枚举项 */
	public int deleteEnumItem(@Param("id") int id);

	/**
	 * 通过枚举ID删除枚举项
	 */
	public int deleteEnumItemByEnumId(@Param("enumId") int enumId);

	/**
	 * 根据获取枚举对象
	 */
	public EnumDBBean getEnumById(@Param("id") int id);

	/** 查询某个枚举下的所有项 */
	public List<EnumDetailDBBean> getEnumDetailByEnumId(@Param("enumId") int enumId);

	/**
	 * 通过KEY查询枚举
	 */
	public EnumDBBean getEnumByKey(@Param("key") String key);
	
	/**
	 * 根据key获取系统参数值
	 * @param key
	 * @return
	 */
	public String getSysParam(@Param("key") String key);
}