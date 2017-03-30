package com.koron.web.bean;
import java.sql.Timestamp;
/**
 * 人员轨迹bean
 * @author Quan
 *
 */
public class TrackBean{
	/**
	 * 录入人员名字
	 */
	private Integer userId;
	private Integer id;
	/**
	 * 经度
	 */
	private double lng;
	/**
	 * 纬度
	 */
	private double lat;
	/**
	 * 录入时间
	 */
	private Timestamp createTime;
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
