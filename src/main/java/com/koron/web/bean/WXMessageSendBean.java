package com.koron.web.bean;
public class WXMessageSendBean{
	private Integer status;
	private Integer id;
	private Integer userid;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 图片id
	 */
	private Integer picUrl;
	/**
	 * 发送时间
	 */
	private String createTime;
	/**
	 * 消息类型
	 */
	private String msgType;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(Integer picUrl) {
		this.picUrl = picUrl;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
}