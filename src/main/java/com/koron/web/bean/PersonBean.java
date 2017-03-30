package com.koron.web.bean;

import java.sql.Timestamp;

public class PersonBean{
	/**
	 * 人员ID
	 */
	private Integer id;
	/**
	 * openid
	 */
	private String openid;
	/**
	 * 人员姓名
	 */
	private String name;
	/**
	 * 人员手机
	 */
	private String phone;
	/**
	 * 经度
	 */
	private Double lng;
	/**
	 * 纬度
	 */
	private Double lat;
	/**
	 * 最后时间
	 */
	private Timestamp inputTime;
	/**
	 * 状态(1上班)
	 */
	private Integer status;
	/**
	 * 所属区域
	 */
	private Integer rangeid;
	/**
	*设置人员ID
	*/
	public PersonBean setId(Integer id){
		this.id = id;
	return this;
	}
	/**
	*获取人员ID
	*/
	public Integer getId(){
		return id;
	}
	/**
	*设置人员姓名
	*/
	public PersonBean setName(String name){
		this.name = name;
	return this;
	}
	/**
	*获取人员姓名
	*/
	public String getName(){
		return name;
	}
	/**
	*设置人员手机
	*/
	public PersonBean setPhone(String phone){
		this.phone = phone;
	return this;
	}
	/**
	*获取人员手机
	*/
	public String getPhone(){
		return phone;
	}
	/**
	*设置经度
	*/
	public PersonBean setLng(Double lng){
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
	public PersonBean setLat(Double lat){
		this.lat = lat;
	return this;
	}
	/**
	*获取纬度
	*/
	public Double getLat(){
		return lat;
	}
	/**
	*设置最后时间
	*/
	public PersonBean setInputTime(Timestamp inputTime){
		this.inputTime = inputTime;
	return this;
	}
	/**
	*获取最后时间
	*/
	public Timestamp getInputTime(){
		return inputTime;
	}
	/**
	*设置状态(1上班)
	*/
	public PersonBean setStatus(Integer status){
		this.status = status;
	return this;
	}
	/**
	*获取状态(1上班)
	*/
	public Integer getStatus(){
		return status;
	}
	/**
	*设置所属区域
	*/
	public PersonBean setRangeid(Integer rangeid){
		this.rangeid = rangeid;
	return this;
	}
	/**
	*获取所属区域
	*/
	public Integer getRangeid(){
		return rangeid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}