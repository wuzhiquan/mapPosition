package com.koron.web.bean;
public class LayerBean{
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父级群组
	 */
	private Integer parentid;
	/**
	 * 是否继承上级
	 */
	private Boolean inherit = false;
	/**
	*设置ID
	*/
	public void setId(Integer id){
		this.id = id;
	}
	/**
	*获取ID
	*/
	public Integer getId(){
		return id;
	}
	/**
	*设置名称
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	*获取名称
	*/
	public String getName(){
		return name;
	}
	/**
	*设置父级群组
	*/
	public void setParentid(Integer parentid){
		this.parentid = parentid;
	}
	/**
	*获取父级群组
	*/
	public Integer getParentid(){
		return parentid;
	}
	/**
	*设置是否继承上级
	*/
	public void setInherit(Boolean inherit){
		this.inherit = inherit;
	}
	/**
	*获取是否继承上级
	*/
	public Boolean getInherit(){
		return inherit;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LayerBean [id=" + id + ", name=" + name + ", parentid=" + parentid + ", inherit=" + inherit + "]";
	}
}