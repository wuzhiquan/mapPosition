package com.swan.weixin.bean;
public class ServiceImageTextMessageBean{
	/**
	 * 调用接口凭证
	 */
	private String access_token;
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，news
	 */
	private String msgtype;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 点击后跳转的链接
	 */
	private String url;
	/**
	 * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
	 */
	private String picurl;
	public void setAccess_token(String access_token){
		this.access_token = access_token;
	}
	public String getAccess_token(){
		return access_token;
	}
	public void setTouser(String touser){
		this.touser = touser;
	}
	public String getTouser(){
		return touser;
	}
	public void setMsgtype(String msgtype){
		this.msgtype = msgtype;
	}
	public String getMsgtype(){
		return msgtype;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return url;
	}
	public void setPicurl(String picurl){
		this.picurl = picurl;
	}
	public String getPicurl(){
		return picurl;
	}
}