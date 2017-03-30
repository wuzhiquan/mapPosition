package com.koron.web.bean;


public class EnumDetailDBBean{
	private Integer id;
	/**
	 * 枚举ID
	 */
	private Integer enumid;
	/**
	 * 值
	 */
	private String key;
	/**
	 * 显示
	 */
	private String value;
	/**
	*设置
	*/
	public void setId(Integer id){
		this.id = id;
	}
	/**
	*获取
	*/
	public Integer getId(){
		return id;
	}
	/**
	*设置枚举ID
	*/
	public void setEnumid(Integer enumid){
		this.enumid = enumid;
	}
	/**
	*获取枚举ID
	*/
	public Integer getEnumid(){
		return enumid;
	}
	/**
	*设置值
	*/
	public void setKey(String key){
		this.key = key;
	}
	/**
	*获取值
	*/
	public String getKey(){
		return key;
	}
	/**
	*设置显示
	*/
	public void setValue(String value){
		this.value = value;
	}
	/**
	*获取显示
	*/
	public String getValue(){
		return value;
	}
}