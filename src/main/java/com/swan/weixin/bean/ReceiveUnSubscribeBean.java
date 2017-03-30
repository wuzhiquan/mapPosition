package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 接收取消订阅消息（推送消息）
 * @author swan
 *
 */
@XmlRootElement(name="xml")
public class ReceiveUnSubscribeBean{
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
	 *  事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	 */
	private String event;
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
	@Override
	public String toString() {
		return "UnSubscribeBean [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", event=" + event + "]";
	}
	
}