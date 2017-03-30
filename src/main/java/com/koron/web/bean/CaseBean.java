package com.koron.web.bean;
public class CaseBean{
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 经度
	 */
	private Double lng;
	/**
	 * 纬度
	 */
	private Double lat;
	private Integer status;
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	private Integer picid;
	private String starttime;
	private String endtime;
	/**
	*设置
	*/
	public CaseBean setId(Integer id){
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
	public CaseBean setName(String name){
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
	*设置描述
	*/
	public CaseBean setDescription(String description){
		this.description = description;
	return this;
	}
	/**
	*获取描述
	*/
	public String getDescription(){
		return description;
	}
	/**
	*设置经度
	*/
	public CaseBean setLng(Double lng){
		this.lng = lng;
	return this;
	}
	/**
	*获取经度
	*/
	public Double getLng(){
		return lng;
	}
	/**
	*设置纬度
	*/
	public CaseBean setLat(Double lat){
		this.lat = lat;
	return this;
	}
	/**
	*获取纬度
	*/
	public Double getLat(){
		return lat;
	}
	public Integer getPicid() {
		return picid;
	}
	public void setPicid(Integer picid) {
		this.picid = picid;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}