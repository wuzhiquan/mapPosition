package com.swan.weixin.bean;

import java.util.HashMap;
/**
 * 客服文本消息bean
 * @author Administrator
 *
 */
public class ServiceTextMessageBean{
	
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，text
	 */
	private String msgtype="text";
	/**
	 * 文本消息内容
	 */
	private HashMap<String, String> text=new HashMap<String, String>();
	
	public void setTouser(String touser){
		this.touser = touser;
	}
	public String getTouser(){
		return touser;
	}
	public String getMsgtype(){
		return msgtype;
	}
	public void setContent(String content){
		text.put("content", content);
	}
	public String getContent(){
		return text.get("content");
	}
}