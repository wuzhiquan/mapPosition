package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 消息是菜单事件，点击时（推送消息）
 * @author swan
 *
 */
@XmlRootElement(name="xml")
public class ReceiveMenuClickBean{
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
	 *  事件类型，CLICK,VIEW
	 */
	private String event;
	/**
	 *  事件KEY值，与自定义菜单接口中KEY值对应
	 */
	private String eventKey;
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
	@XmlElement(name="EventKey")
	public String getEventKey() {
		return eventKey;
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
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	@Override
	public String toString() {
		return "MenuClickBean [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", event=" + event + ", eventKey=" + eventKey + "]";
	}
	
	
}