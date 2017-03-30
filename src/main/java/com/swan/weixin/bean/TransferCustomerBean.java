package com.swan.weixin.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class TransferCustomerBean{
	/**
	 * 接收方帐号（收到的OpenID）
	 */
	private String toUserName;
	/**
	 * 开发者微信号
	 */
	private String fromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private String createTime;
	/**
	 * 类transfer_customer_service
	 */
	private String msgType="transfer_customer_service";
	
	
	public void setFromUserName(String fromUserName){
		this.fromUserName = fromUserName;
	}
	@XmlElement(name="FromUserName")
	public String getFromUserName(){
		return fromUserName;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	@XmlElement(name="CreateTime")
	public String getCreateTime(){
		return createTime;
	}
	@XmlElement(name="msgType")
	public String getMsgType(){
		return msgType;
	}
	@XmlElement(name="msgType")
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
}