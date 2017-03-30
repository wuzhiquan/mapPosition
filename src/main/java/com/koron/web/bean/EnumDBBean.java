package com.koron.web.bean;

public class EnumDBBean {
	private Integer id;
	/**
	 * 标识
	 */
	private String key;
	/**
	 * 是否位运算
	 */
	private Boolean isbit;
	/**
	 * 类型(0字符串，1整数，2长整型）
	 */
	private int type;
	/**
	 * 动态参数，为字字符串时为非动态
	 */
	private String param;
	/**
	 * 设置
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置标识
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取标识
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return 获取类型
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param 设置类型
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return 获取是否位运算
	 */
	public Boolean getIsbit() {
		return isbit;
	}

	/**
	 * @param 设置是否位运算
	 */
	public void setIsbit(Boolean isbit) {
		this.isbit = isbit;
	}

	/**
	 * @return 获取动态参数，为字字符串时为非动态
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param 设置动态参数，为字字符串时为非动态
	 */
	public void setParam(String param) {
		this.param = param;
	}

}