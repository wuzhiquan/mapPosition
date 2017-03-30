package com.koron.web.bean;

import org.koron.ebs.util.field.FieldBean;

public class DefineFieldBean extends FieldBean implements Comparable<DefineFieldBean> {
	
	/**
	 * 必填
	 */
	public static final Integer NOTNULL = 0x80000000;
	/**
	 * 唯一
	 */
	public static final Integer UNIQUE = 0x40000000;
	/**
	 * 层别ID
	 */
	private Integer layerid;
	/**
	 * 长度
	 */
	private Integer length;
	/**
	 * 缺省值
	 */
	private String defaultValue;
	/**
	 * 标旗(1必填,2唯一,4递增)
	 */
	private Integer flag;
	/**
	 * 系列号
	 */
	private Integer sn;

	public DefineFieldBean() {
		super("", 0);
	}

	/**
	 * 设置所属分组
	 */
	public void setLayerid(Integer layerid) {
		this.layerid = layerid;
	}

	/**
	 * 获取所属分组
	 */
	public Integer getLayerid() {
		return layerid;
	}

	/**
	 * 设置长度
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * 获取长度
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * 设置缺省值
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * 获取缺省值
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 设置标旗(1必填,2唯一,4递增)
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * 获取标旗(1必填,2唯一,4递增)
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @return 获取系列号
	 */
	public Integer getSn() {
		return sn;
	}

	/**
	 * @param 设置系列号
	 */
	public void setSn(Integer sn) {
		this.sn = sn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DefineFieldBean [getLayerid()=" + getLayerid() + ", getLength()=" + getLength() + ", getDefault()="
				+ getDefaultValue() + ", getFlag()=" + getFlag() + ", getName()=" + getName() + ", getCaption()="
				+ getCaption() + ", getDesc()=" + getDesc() + ", getType()=" + getType() + ", getParam()=" + getParam()
				+ ", getClassName()=" + getClassName() + ", getId()=" + getId() + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.koron.ebs.util.field.FieldBean#clone()
	 */
	@Override
	public DefineFieldBean clone() {
		return (DefineFieldBean) super.clone();
	}

	@Override
	public int compareTo(DefineFieldBean o) {
		if (o == null)
			return 1;
		return getSn() - o.getSn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof DefineFieldBean)
			return getName().equals(((DefineFieldBean) obj).getName());
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getName().hashCode();
	}
}