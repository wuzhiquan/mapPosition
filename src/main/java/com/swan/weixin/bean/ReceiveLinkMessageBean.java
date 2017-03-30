package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 链接消息（普通消息）
 * @author swan
 *
 */
@XmlRootElement(name="xml")
public class ReceiveLinkMessageBean {
	/**
	 * 接收方微信号
	 */
	private String toUserName;
	/**
	 * 发送方微信号，若为普通用户，则是一个OpenID
	 */
	private String fromUserName;
	/**
	 * 消息创建时间
	 */
	private String createTime;
	/**
	 * 消息类型，link
	 */
	private String msgType;
	/**
	 * 消息标题
	 */
	private String title;
	/**
	 * 消息描述
	 */
	private String description;
	/**
	 * 消息链接
	 */
	private String url;
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
	@XmlElement(name="Title")
	public String getTitle() {
		return title;
	}
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	@XmlElement(name="Url")
	public String getUrl() {
		return url;
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
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "LinkMessageBean [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", title=" + title + ", description=" + description + ", url=" + url + "]";
	}

}