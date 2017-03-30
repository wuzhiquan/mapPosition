package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 接收到公众号自动发送定位消息(推送消息)
 * @author swan
 *
 */
@XmlRootElement(name="xml")
public class ReceiveWeixinLocationBean{
	/**
	 * 开发者微信号
	 */
	private String toUserName;
	/**
	 *  发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	/**
	 *  消息创建时间 （整型）
	 */
	private String createTime;
	/**
	 *  消息类型，event
	 */
	private String msgType;
	/**
	 *  事件类型，LOCATION
	 */
	private String event;
	/**
	 *  地理位置纬度
	 */
	private String latitude;
	/**
	 *  地理位置经度
	 */
	private String longitude;
	/**
	 *  地理位置精度
	 */
	private String precision;
	@XmlElement(name="ToUserName")
	public String getToUserName() {
		return toUserName;
	}
	@XmlElement(name="FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}
	@XmlElement(name="CreateTime")
	public String getCreateTime() {
		return createTime;
	}
	@XmlElement(name="MsgType")
	public String getMsgType() {
		return msgType;
	}
	@XmlElement(name="Event")
	public String getEvent() {
		return event;
	}
	@XmlElement(name="Latitude")
	public String getLatitude() {
		return latitude;
	}
	@XmlElement(name="Longitude")
	public String getLongitude() {
		return longitude;
	}
	@XmlElement(name="Precision")
	public String getPrecision() {
		return precision;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	@Override
	public String toString() {
		return "WeixinLocationBean [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", event=" + event + ", latitude=" + latitude + ", longitude=" + longitude + ", precision=" + precision + "]";
	}
	
}