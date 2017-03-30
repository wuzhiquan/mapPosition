package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
/**
 * 接收到用户发送的定位信息（普通消息）
 */
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 定位消息(普通消息)
 * @author swan
 *
 */
@XmlRootElement(name="xml")
public class ReceiveLocationMessageBean {
	/**
	 * 开发者微信号
	 */
	private String toUserName;
	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private String createTime;
	/**
	 * location
	 */
	private String msgType;
	/**
	 * 地理位置维度
	 */
	private String location_X;
	/**
	 * 地理位置经度
	 */
	private String location_Y;
	/**
	 * 地图缩放大小
	 */
	private String scale;
	private String msgId;
	/**
	 * 地理位置信息
	 */
	private String label;
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
	@XmlElement(name="Location_X")
	public String getLocation_X() {
		return location_X;
	}
	@XmlElement(name="Location_Y")
	public String getLocation_Y() {
		return location_Y;
	}
	@XmlElement(name="Scale")
	public String getScale() {
		return scale;
	}
	@XmlElement(name="Label")
	public String getLabel() {
		return label;
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
	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}
	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "LocationMessageBean [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", location_X=" + location_X + ", location_Y=" + location_Y + ", scale=" + scale + ", label=" + label + "]";
	}
	@XmlElement(name="MsgId")
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
}