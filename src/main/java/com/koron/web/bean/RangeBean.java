package com.koron.web.bean;
public class RangeBean{
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 边界定位
	 */
	private String position;
	/**
	 * 边界颜色
	 */
	private String bordercolor;
	/**
	 * 背景颜色
	 */
	private String bgcolor;
	/**
	 * 计划人数
	 */
	private Integer plantpeople;
	/**
	*设置
	*/
	public RangeBean setId(Integer id){
		this.id = id;
	return this;
	}
	/**
	*获取
	*/
	public Integer getId(){
		return id;
	}
	/**
	*设置名称
	*/
	public RangeBean setName(String name){
		this.name = name;
	return this;
	}
	/**
	*获取名称
	*/
	public String getName(){
		return name;
	}
	/**
	*设置边界定位
	*/
	public RangeBean setPosition(String position){
		this.position = position;
	return this;
	}
	/**
	*获取边界定位
	*/
	public String getPosition(){
		return position;
	}
	/**
	*设置边界颜色
	*/
	public RangeBean setBordercolor(String bordercolor){
		this.bordercolor = bordercolor;
	return this;
	}
	/**
	*获取边界颜色
	*/
	public String getBordercolor(){
		return bordercolor;
	}
	/**
	*设置背景颜色
	*/
	public RangeBean setBgcolor(String bgcolor){
		this.bgcolor = bgcolor;
	return this;
	}
	/**
	*获取背景颜色
	*/
	public String getBgcolor(){
		return bgcolor;
	}
	/**
	*设置计划人数
	*/
	public RangeBean setPlantpeople(Integer plantpeople){
		this.plantpeople = plantpeople;
	return this;
	}
	/**
	*获取计划人数
	*/
	public Integer getPlantpeople(){
		return plantpeople;
	}
}