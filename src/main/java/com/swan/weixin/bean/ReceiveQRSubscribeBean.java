package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 未关注时扫描带参数二维码（消息推送）
 * @author swan
 *
 */
@XmlRootElement(name="xml")
public class ReceiveQRSubscribeBean{
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
	 *  事件类型，subscribe
	 */
	private String event;
	/**
	 *  事件KEY值，qrscene_为前缀，后面为二维码的参数值
	 */
	private String eventKey;
	/**
	 *  二维码的ticket，可用来换取二维码图片
	 */
	private String ticket;
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
	@XmlElement(name="Ticket")
	public String getTicket() {
		return ticket;
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
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "QRSubscribeBean [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", event=" + event + ", eventKey=" + eventKey + ", ticket=" + ticket + "]";
	}

}