package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class ReplyCustomerServiceBean {
	/**
	 * 发送人
	 */
	private String toUserName;
	/**
	 * 接收人
	 */
	private String fromUserName;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 消息类型
	 */
	private String msgType= "transfer_customer_service";
	/**
	 * @return 获取发送人
	 */
	@XmlElement(name="ToUserName")
	public String getToUserName() {
		return toUserName;
	}
	/**
	 * @param toUserName 设置发送人
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	/**
	 * @return 获取接收人
	 */
	@XmlElement(name="FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}
	/**
	 * @param fromUserName 设置接收人
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	/**
	 * @return 获取创建时间
	 */
	@XmlElement(name="CreateTime")
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime 设置创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return 获取消息类型
	 */
	@XmlElement(name="MsgType")
	public String getMsgType() {
		return msgType;
	}
	
}
